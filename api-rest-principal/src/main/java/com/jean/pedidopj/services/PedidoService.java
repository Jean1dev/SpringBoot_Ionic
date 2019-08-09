package com.jean.pedidopj.services;

import com.jean.pedidopj.domain.*;
import com.jean.pedidopj.exceptions.AuthorizationException;
import com.jean.pedidopj.exceptions.ObjectNotFoundException;
import com.jean.pedidopj.repositories.ItemPedidoRepository;
import com.jean.pedidopj.repositories.PagamentoRepository;
import com.jean.pedidopj.repositories.PedidoRepository;
import com.jean.pedidopj.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	 @Autowired
	private PagamentoRepository pagamentoRepository;

	 @Autowired
	private ItemPedidoRepository itemPedidoRepository;
 
	@Autowired
	private ClienteService clienteService;

	 @Autowired
	private ProdutoService produtoService;
 
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS userSS = UserService.authenticated();
		if (userSS == null) {
			throw new AuthorizationException("Acesso negado");
		}

		Cliente cliente = clienteService.find(userSS.getId());
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repo.findByCliente(cliente, pageRequest);
	}

}
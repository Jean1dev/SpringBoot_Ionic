const stompit = require('stompit');
const connectOptions = require('./conexao.config')

const sendParams = {
    'destination': 'queue.sample',
    'content-type': 'application/json'
};

// setInterval(() => {

    stompit.connect(connectOptions,  (error, client) => { 
        let i = 1
        var frame = client.send(sendParams);
        frame.end('enviado via worker ' + i); 
        i += 1
        client.disconnect(function(error){
            if(error){
                console.log('Error while disconnecting: ' + error.message);
                return;
            }
            console.log('Sent message');
        });
    })
    
// }, 5000)
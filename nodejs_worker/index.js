// https://github.com/gdaws/node-stomp
const stompit = require('stompit');

const connectOptions = {
    'host': 'localhost',
    'port': 61613,
    'connectHeaders': {
        'host': '/',
        'login': '',
        'passcode': '',
        'heart-beat': '5000,5000'
    }
};

const sendParams = {
    'destination': 'queue.sample',
    'content-type': 'application/json'
};

stompit.connect(connectOptions, function (error, client) {

    if (error) {
        console.log('connect error ' + error.message);
        return;
    }

    var frame = client.send(sendParams); 
    
    frame.end('enviado via worker');

    const subscribeInQueue = {
        'destination': 'queue.sample',
        'ack': 'client-individual'
    };

    const subscribeInTopic = {
        'destination': 'topic.sample',
        'ack': 'client-individual'
    }

    // client.subscribe(subscribeInTopic, (error, message) => {
    //     console.log(message)
    //     client.ack(message);
    // })

    client.subscribe(subscribeInQueue, function (error, message) {

        if (error) {
            console.log('subscribe error ' + error.message);
            return;
        }

        message.readString('utf-8', function (error, body) {

            if (error) {
                console.log('read message error ' + error.message);
                return;
            }

            console.log('received message: ' + body);
            client.ack(message);

        });
    });
});
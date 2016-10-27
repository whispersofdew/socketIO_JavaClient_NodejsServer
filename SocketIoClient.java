package ru.ids360.x360FileStorage;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;



/**
 * Created by korolev on 25.10.16.
 */
public class SocketIoClient {
    Socket socket = null;
    public Socket createSocket () throws URISyntaxException {

        IO.Options opts = new IO.Options();

        //opts.transports = new String [] {"websocket"};
        opts.query ="workspace=123";
        opts.path = "/fileStorage";
        socket = IO.socket("http://127.0.0.1:3000", opts);
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                socket.emit("foo", "hi");
                socket.disconnect();
            }

        }).on("event", new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });
        socket.connect();

        return socket;
    }}

    public static void main(String[] args) throws URISyntaxException {
        SocketIoClient socketIoClient = new SocketIoClient();
        Socket currentSocket = socketIoClient.createSocket();
        //currentSocket.close();
    }
}

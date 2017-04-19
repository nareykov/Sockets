package source;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by narey on 19.04.2017.
 */
public class ServerHandler extends Thread{

    private final ServerSocket server;

    ServerHandler(ServerSocket server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                ClientHandle handle = new ClientHandle(client);
                handle.start();
                ServerLoader.handlers.put(client, handle);
            } catch (SocketException e) {
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

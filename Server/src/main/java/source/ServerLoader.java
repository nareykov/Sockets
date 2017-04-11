package source;

import source.ClientHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by narey on 23.03.2017.
 */
public class ServerLoader {

    private static ServerSocket server;
    private static Map<Socket, ClientHandle> handlers = new HashMap<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    private static void start() {
        try {
            server = new ServerSocket(8888);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void handle() {
        while (true) {
            try {
                Socket client = server.accept();
                ClientHandle handle = new ClientHandle(client);
                handle.start();
                handlers.put(client, handle);
                new ClientHandle(client);

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

    public static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ClientHandle getHandle(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }
}

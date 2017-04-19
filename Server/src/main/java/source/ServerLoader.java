package source;

import packet.OPacket;
import source.ClientHandle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by narey on 23.03.2017.
 */
public class ServerLoader {

    private static ServerSocket server;
    private static ServerHandler handler;
    public static Map<Socket, ClientHandle> handlers = new HashMap<>();

    public static void main(String[] args) {
        start();
        handle();
        end();
    }

    public static void sendPacket(Socket receiver, OPacket packet) {
        try {
            DataOutputStream dos = new DataOutputStream(receiver.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        handler = new ServerHandler(server);
        handler.start();
        readChat();
    }

    private static void readChat() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.println(line);
                if (line.equals("/end")) {
                    end();
                } else {
                    System.out.println("unknown command");
                }
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ServerHandler getServerHandler() {
        return handler;
    }

    public static void end() {
        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static ClientHandle getHandle(Socket socket) {
        return handlers.get(socket);
    }

    public static void invalidate(Socket socket) {
        handlers.remove(socket);
    }
}

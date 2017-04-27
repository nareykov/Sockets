package source;

import source.classes.ZIP;
import source.packet.OPacket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
    private static String adminName = "admin";
    private static String adminPass = "admin";
    private static File root = new File("Archives");

    public static void main(String[] args) {

        start();
        handle();
        end();
    }

    private static void start() {
        root.mkdir();

        File idFile = new File("idFile");
        if (!idFile.exists()) {
            try {
                idFile.createNewFile();
                PrintWriter out = new PrintWriter(idFile.getAbsoluteFile());
                out.print(0);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File typeParserFile = new File("typeParserFile");
        if (!typeParserFile.exists()) {
            try {
                typeParserFile.createNewFile();
                PrintWriter out = new PrintWriter(typeParserFile.getAbsoluteFile());
                out.print(2);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    public static String getAdminName() {
        return adminName;
    }

    public static String getAdminPass() {
        return adminPass;
    }

    public static File getRoot() {
        return root;
    }
}

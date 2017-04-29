package source;

import org.apache.log4j.Logger;
import source.classes.DOMParser;
import source.classes.ZIP;
import source.packet.OPacket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

/**
 * Главный класс сервера
 */
public class ServerLoader {

    private static ServerSocket server;
    private static ServerHandler handler;
    public static Map<Socket, ClientHandle> handlers = new HashMap<>();
    private static String adminName = "admin";
    private static String adminPass = "admin";
    private static File root = new File("Archives");

    private static final Logger log = Logger.getLogger(ServerLoader.class);

    /**
     * Запуск сервера
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Сервер запущен");
        start();
        handle();
        end();
    }

    /**
     * Подготовка к работе сервера
     */
    private static void start() {
        log.info("Запуск сервера");
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

    /**
     * Старт нового потока ServerHandler
     */
    private static void handle() {
        handler = new ServerHandler(server);
        handler.start();
        read();
    }

    /**
     * Читает команды из консоли
     */
    private static void read() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
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

    /**
     * Завершение работы сервера
     */
    public static void end() {
        log.info("Завершение работы сервера");
        try {
            server.close();
        } catch (IOException e) {
            log.error(e.toString());
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

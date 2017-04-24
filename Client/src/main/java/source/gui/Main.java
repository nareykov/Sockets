package source.gui;

import source.packet.OPacket;
import source.packet.PacketManager;

import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by narey on 11.04.2017.
 */
public class Main {

    private static EnterWindow enterWindow;
    private static RegisterWindow registerWindow;
    private static AdminWindow adminWindow;
    private static MainWindow mainWindow;

    private static Socket socket;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                connect();
                enterWindow = new EnterWindow();
            }
        });
    }

    private synchronized static void waitServer() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                if (ois.available() <= 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                short id = ois.readShort();
                OPacket packet = PacketManager.getPacket(id);
                packet.read(ois);
                packet.handle();
                break;
            } catch (Exception e) {
                e.printStackTrace();
                end();
            }
        }
    }

    public static void end() {
        try {
            System.out.println("KEKEKEKEKEK");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void connect() {
        try {
            socket = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void sendPacket(OPacket packet) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeShort(packet.getId());
            packet.write(oos);
            oos.flush();
            System.out.println("Отправлен пакет" + packet.getId());
            waitServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EnterWindow getEnterWindow() {
        return enterWindow;
    }

    public static void setEnterWindow(EnterWindow enterWindow) {
        Main.enterWindow = enterWindow;
    }

    public static RegisterWindow getRegisterWindow() {
        return registerWindow;
    }

    public static void setRegisterWindow(RegisterWindow registerWindow) {
        Main.registerWindow = registerWindow;
    }

    public static AdminWindow getAdminWindow() {
        return adminWindow;
    }

    public static void setAdminWindow(AdminWindow adminWindow) {
        Main.adminWindow = adminWindow;
    }

    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    public static void setMainWindow(MainWindow mainWindow) {
        Main.mainWindow = mainWindow;
    }
}

package source.gui;

import source.packet.OPacket;
import source.packet.PacketManager;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by narey on 11.04.2017.
 */
public class Main {

    private static Socket socket;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                connect();
                new EnterWindow();
            }
        });
    }

    private static void waitServer() {
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
            } catch (IOException e) {
                e.printStackTrace();
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

    public static void sendPacket(OPacket packet) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeShort(packet.getId());
            packet.write(oos);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

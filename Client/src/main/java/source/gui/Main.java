package source.gui;

import org.apache.log4j.Logger;
import source.packet.OPacket;
import source.packet.PacketManager;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Главный класс юзера
 */
public class Main {

    private static EnterWindow enterWindow;
    private static RegisterWindow registerWindow;
    private static AdminWindow adminWindow;
    private static MainWindow mainWindow;
    private static CreateCaseWindow createCaseWindow;

    private static Socket socket;
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    private static final Logger log = Logger.getLogger(Main.class);

    /**
     * Поключается к серверу и открывает окнот входа
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                connect();
                enterWindow = new EnterWindow();
            }
        });
    }

    /**
     * Ожиданет ответ сервера и обрабатывает его
     */
    private synchronized static void waitServer() {
        log.info("Ожидание ответа сервера");
        while (true) {
            try {
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
                log.info("Получен пакет: " + id);
                break;
            } catch (Exception e) {
                log.error(e.toString());
                end();
            }
        }
    }

    /**
     * Отключает клиента от сервера
     */
    public static void end() {
        try {
            log.info("Отключение");
            oos.close();
            ois.close();
            socket.close();
        } catch (IOException e) {
            log.error(e.toString());
        }
        System.exit(0);
    }

    /**
     * Подключает клиента к серверу
     */
    private static void connect() {
        log.info("Подключение");
        try {
            socket = new Socket("localhost", 8888);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            log.error(e.toString());
        }
    }

    /**
     * Отправляет пакет серверу и ожидает ответ
     * @param packet
     */
    public synchronized static void sendPacket(OPacket packet) {
        try {
            oos.writeShort(packet.getId());
            packet.write(oos);
            oos.flush();
            log.info("Отправлен пакет" + packet.getId());
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

    public static CreateCaseWindow getCreateCaseWindow() {
        return createCaseWindow;
    }

    public static void setCreateCaseWindow(CreateCaseWindow createCaseWindow) {
        Main.createCaseWindow = createCaseWindow;
    }
}

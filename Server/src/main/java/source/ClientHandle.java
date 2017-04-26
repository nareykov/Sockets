package source;

import org.omg.CORBA.Object;
import source.classes.User;
import source.packet.OPacket;
import source.packet.PacketManager;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by narey on 23.03.2017.
 */
public class ClientHandle extends Thread {
    private final Socket client;
    private User user = new User("Noname", "Noname");
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    ClientHandle(Socket client) throws IOException {
        this.client = client;
        ois = new ObjectInputStream(client.getInputStream());
        oos = new ObjectOutputStream(client.getOutputStream());
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                if (ois.available() > 0) {
                    short id = ois.readShort();
                    OPacket packet = PacketManager.getPacket(id);
                    packet.read(ois);
                    packet.handle();
                    sendPacket(packet);
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SocketException | EOFException e) {
                e.printStackTrace();
                ServerLoader.handlers.remove(client);
                try {
                    oos.close();
                    ois.close();
                    client.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.out.println(user.getNickname() + " interrupted");
                currentThread().interrupt();
                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void sendPacket(OPacket packet) {
        try {
            oos.writeShort(packet.getId());
            packet.write(oos);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

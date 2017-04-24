package source;

import org.omg.CORBA.Object;
import source.classes.User;
import source.packet.OPacket;
import source.packet.PacketManager;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by narey on 23.03.2017.
 */
public class ClientHandle extends Thread {
    private final Socket client;
    private User user = new User("Noname", "Noname");

    ClientHandle(Socket client) {
        this.client = client;
    }

    @Override
    public synchronized void run() {
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                if (ois.available() > 0) {
                    short id = ois.readShort();
                    OPacket packet = PacketManager.getPacket(id);
                    packet.setSocket(client);
                    packet.read(ois);
                    packet.handle();
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

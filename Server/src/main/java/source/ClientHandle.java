package source;

import packet.OPacket;
import packet.PacketAuthorize;
import packet.PacketManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by narey on 23.03.2017.
 */
public class ClientHandle extends Thread {
    private final Socket client;
    public String nickname = "Noname";

    ClientHandle(Socket client) {
        this.client = client;
        //start();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void run() {
        while (true) {

            if (!readData()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private boolean readData() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() > 0) {
                short id = dis.readShort();
                OPacket packet = PacketManager.getPacket(id);
                packet.setSocket(client);
                packet.read(dis);
                packet.handle();
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }
}

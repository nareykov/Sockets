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
    public String nickname;

    ClientHandle(Socket client) {
        this.client = client;
        start();
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
            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                if (dis.available() > 0) { //если количество доступных байт для чтения больше нуля
                    System.out.println(dis.readInt());
                    ServerLoader.end();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void readData() {
        try {
            DataInputStream dis = new DataInputStream(client.getInputStream());
            if (dis.available() > 0) {
                short id = dis.readShort();
                OPacket packet = PacketManager.getPacket(id);
                packet.setSocket(client);
                packet.read(dis);
                packet.handle();
            } else {
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void invalidate() {
        ServerLoader.invalidate(client);
    }
}

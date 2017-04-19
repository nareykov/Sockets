package source;

import com.sun.xml.internal.ws.api.message.Packet;
import packet.OPacket;
import packet.PacketAuthorize;
import packet.PacketManager;
import packet.PacketMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by narey on 23.03.2017.
 */
public class ClientLoader {

    private static Socket socket;
    private static boolean sentNickname = false;

    public static void main(String[] args) {
        connect();
        handle();
        end();
    }

    private static void handle() {
        Thread handle = new Thread() {

            @Override
            public void run() {
                while (true) {
                    try {
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        if (dis.available() <= 0) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            continue;
                        }
                        short id = dis.readShort();
                        OPacket packet = PacketManager.getPacket(id);
                        packet.read(dis);
                        packet.handle();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        handle.start();

        readChat();
    }

    private static void readChat() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            if (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.equals("/end")) {
                    end();
                }
                if (!sentNickname) {
                    sentNickname = true;
                    sendPacket(new PacketAuthorize(line));
                    continue;
                }
                sendPacket(new PacketMessage(null, line));
            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void end() {
        try {
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
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeShort(packet.getId());
            packet.write(dos);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

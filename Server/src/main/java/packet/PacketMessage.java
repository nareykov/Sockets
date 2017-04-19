package packet;

import source.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by narey on 19.04.2017.
 */
public class PacketMessage extends OPacket{

    private String sender;
    private String message;

    public PacketMessage() {

    }

    public PacketMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public short getId() {
        return 2;
    }

    public void write(DataOutputStream dos) throws IOException {
        dos.writeUTF(sender);
        dos.writeUTF(message);
    }

    public void read(DataInputStream dis) throws IOException {
        message = dis.readUTF();
    }

    public void handle() {
        sender = ServerLoader.getHandle(getSocket()).getNickname();
        ServerLoader.handlers.keySet().forEach(s -> ServerLoader.sendPacket(s, this));
        System.out.println(String.format("[%s] %s", sender, message));
    }
}
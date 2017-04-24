package source.packet;

import source.DataBase;
import source.ServerLoader;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 24.04.2017.
 */
public class PacketChangePriority extends OPacket {

    private String username;

    private int priority;

    DataBase db = new DataBase();

    public PacketChangePriority() {

    }

    public PacketChangePriority(String username, int priority) {
        this.username = username;
        this.priority = priority;
    }

    @Override
    public short getId() {
        return 4;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {

    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        username = ois.readUTF();
        priority = ois.readInt();
    }

    @Override
    public void handle() {
        db.connectToDataBase();
        db.changePriority(username, priority);
        db.closeDataBase();
        ServerLoader.sendPacket(socket, new PacketChangePriority());
    }
}


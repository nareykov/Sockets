package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.User;

import java.io.*;

/**
 * Created by narey on 20.04.2017.
 */
public class PacketRegister extends OPacket {

    private User user;

    DataBase db = new DataBase();

    public PacketRegister() {

    }

    public PacketRegister(User user) {
        this.user = user;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {

    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        try {
            user = (User) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        ServerLoader.getHandle(socket).setUser(user);
        System.out.println("Authorized " + user.getNickname());
        db.connectToDataBase();
        db.closeDataBase();
    }
}

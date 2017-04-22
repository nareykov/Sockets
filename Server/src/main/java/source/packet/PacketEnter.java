package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 22.04.2017.
 */
public class PacketEnter extends OPacket {

    private User user;

    DataBase db = new DataBase();

    private static String answer;

    public PacketEnter() {

    }

    public PacketEnter(User user) {
        this.user = user;
    }

    @Override
    public short getId() {
        return 2;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeUTF(answer);
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
        if (user.getNickname().equals(ServerLoader.getAdminName()) && user.getPassword().equals(ServerLoader.getAdminPass())) {
            answer = "Admin";
        } else {
            db.connectToDataBase();
            if (db.enter(user.getNickname(), user.getPassword())) {
                answer = "Success";
            } else {
                answer = "Incorrect login or password";

            }
            db.closeDataBase();
        }
        ServerLoader.sendPacket(socket, new PacketEnter());
    }
}

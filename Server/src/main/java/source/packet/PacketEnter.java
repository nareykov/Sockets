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
    private static int priority;

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
        oos.writeInt(priority);
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
            priority = 2;
        } else {
            db.connectToDataBase();
            if (db.enter(user.getNickname(), user.getPassword())) {
                answer = "Success";
                priority = db.getPriority(user.getNickname());
            } else {
                answer = "Incorrect login or password";
                priority = -1;
            }
            db.closeDataBase();
        }
        ServerLoader.sendPacket(socket, new PacketEnter());
    }
}

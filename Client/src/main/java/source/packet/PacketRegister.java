package source.packet;

import org.omg.CORBA.Object;
import source.classes.User;

import java.io.*;

/**
 * Created by narey on 20.04.2017.
 */
public class PacketRegister extends OPacket{

    private User user;

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
        oos.writeObject(user);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {

    }

}

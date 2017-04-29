package source.packet;

import source.classes.User;
import source.gui.EnterWindow;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Сообщение входа юзера
 */
public class PacketEnter extends OPacket {

    private User user;

    private String answer;
    private int priority;

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
        oos.writeObject(user);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        answer = ois.readUTF();
        priority = ois.readInt();
    }

    @Override
    public void handle() {
        EnterWindow.setAnswer(answer);
        EnterWindow.setPriority(priority);
    }
}

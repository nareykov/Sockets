package source.packet;

import source.classes.Case;
import source.gui.CreateCaseWindow;
import source.gui.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Сообщение о просмотре архива
 */
public class PacketOpenCase extends OPacket {

    private Case openCase;
    private int id;

    public PacketOpenCase() {

    }

    public PacketOpenCase(int id) {
        this.id = id;
    }

    @Override
    public short getId() {
        return 7;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeInt(id);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        try {
            openCase = (Case) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        Main.setCreateCaseWindow(new CreateCaseWindow(openCase));
    }
}

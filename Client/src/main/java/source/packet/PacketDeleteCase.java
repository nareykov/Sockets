package source.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Сообщение об удалении архива
 */
public class PacketDeleteCase extends OPacket {

    private int id;

    public PacketDeleteCase() {

    }

    public PacketDeleteCase(int id) {
        this.id = id;
    }

    @Override
    public short getId() {
        return 9;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeInt(id);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {

    }
}

package source.packet;

import source.classes.Case;
import source.classes.DOMParser;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 26.04.2017.
 */
public class PacketChangeCase extends OPacket {

    int id;
    File file;
    String name;

    public PacketChangeCase() {

    }

    public PacketChangeCase(int id, Case newCase) {
        this.id = id;
        name = newCase.getSurname() + " " + newCase.getName();
        file = DOMParser.writeXML(newCase);
    }

    @Override
    public short getId() {
        return 8;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeObject(file);
        oos.writeUTF(name);
        oos.writeInt(id);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {

    }
}

package source.packet;

import source.ServerLoader;
import source.classes.Case;
import source.classes.DOMParser;
import source.classes.Parser;
import source.classes.ZIP;

import java.io.File;
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

    public PacketOpenCase(Case openCase) {
        this.openCase = openCase;
    }

    @Override
    public short getId() {
        return 7;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeObject(openCase);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        id = ois.readInt();
    }

    @Override
    public void handle() {
        openCase = Parser.readXML(ZIP.fromZIP(new File(ServerLoader.getRoot().getAbsolutePath()+ "\\" + id + ".zip")));
    }
}
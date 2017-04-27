package source.packet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 27.04.2017.
 */
public class PacketChangeParser extends OPacket {

    int typeOfParser;

    public PacketChangeParser() {

    }

    public PacketChangeParser(int typeOfParser) {
        this.typeOfParser = typeOfParser;
    }

    @Override
    public short getId() {
        return 10;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeInt(typeOfParser);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {

    }
}

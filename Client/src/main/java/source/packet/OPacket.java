package source.packet;

import java.io.*;

/**
 * Created by narey on 23.03.2017.
 */
public abstract class OPacket {

    public abstract short getId();

    public abstract void write(ObjectOutputStream dos) throws IOException;

    public abstract void read(ObjectInputStream dis) throws IOException;

    public abstract void handle();
}

package source.packet;

import java.io.*;
import java.net.Socket;

/**
 * Created by narey on 23.03.2017.
 */
public abstract class OPacket {

    public abstract short getId();

    public abstract void write(ObjectOutputStream oos) throws IOException;

    public abstract void read(ObjectInputStream ois) throws IOException;

    public abstract void handle();

}

package source.packet;

import java.io.*;
import java.net.Socket;

/**
 * Created by narey on 23.03.2017.
 */
public abstract class OPacket {

    public Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public abstract short getId();

    public abstract void write(ObjectOutputStream oos) throws IOException;

    public abstract void read(ObjectInputStream ois) throws IOException;

    public abstract void handle();

}

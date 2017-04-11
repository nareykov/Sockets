package packet;

import com.sun.deploy.util.SessionState;
import source.ClientLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by narey on 23.03.2017.
 */
public abstract class OPacket {

    public abstract short getId();

    public abstract void write(DataOutputStream dos) throws IOException;

    public abstract void read(DataInputStream dis) throws IOException;

}

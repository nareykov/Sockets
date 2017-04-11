package packet;

import java.io.DataInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by narey on 23.03.2017.
 */
public class PacketManager {

    private final static Map<Short, Class<? extends OPacket>> packets = new HashMap();

    static  {
        packets.put((short) 1, PacketAuthorize.class);
        //..........
        //..........
    }


    public static OPacket getPacket(short id) {
        try {
            return packets.get(id).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}

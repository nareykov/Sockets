package packet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by narey on 19.04.2017.
 */
public class PacketManager {

    private final static Map<Short, Class<? extends OPacket>> packets = new HashMap();

    static  {
        packets.put((short) 1, PacketAuthorize.class);
        packets.put((short) 2, PacketMessage.class);
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

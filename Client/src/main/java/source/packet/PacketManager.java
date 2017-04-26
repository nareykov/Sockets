package source.packet;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by narey on 19.04.2017.
 */
public class PacketManager {

    private final static Map<Short, Class<? extends OPacket>> packets = new HashMap();

    static  {
        packets.put((short) 1, PacketRegister.class);
        packets.put((short) 2, PacketEnter.class);
        packets.put((short) 3, PacketLoadAdminTable.class);
        packets.put((short) 4, PacketChangePriority.class);
        packets.put((short) 5, PacketLoadMainTable.class);
        packets.put((short) 6, PacketCreateCase.class);
        packets.put((short) 7, PacketOpenCase.class);
        packets.put((short) 8, PacketChangeCase.class);
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

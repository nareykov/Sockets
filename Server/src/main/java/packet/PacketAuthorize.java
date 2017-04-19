package packet;

import source.ServerLoader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by narey on 23.03.2017.
 */
public class PacketAuthorize extends OPacket {
    private String nickname;

    public PacketAuthorize() {

    }

    public PacketAuthorize(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public short getId() {
        return 1;
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {

    }

    @Override
    public void read(DataInputStream dis) throws IOException {
        nickname = dis.readUTF();//для сервера)))))
    }

    @Override
    public void handle() {
        ServerLoader.getHandle(socket).setNickname(nickname);
        System.out.println("Authorized " + nickname);
    }
}

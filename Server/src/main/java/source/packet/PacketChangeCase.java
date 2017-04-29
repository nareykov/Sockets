package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.Case;
import source.classes.DOMParser;
import source.classes.ZIP;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Сообщение о изменении архива
 */
public class PacketChangeCase extends OPacket {

    int id;
    File file;
    String name;
    DataBase db = new DataBase();

    public PacketChangeCase() {

    }

    @Override
    public short getId() {
        return 8;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {

    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        try {
            file = (File) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        name = ois.readUTF();
        id = ois.readInt();
    }

    @Override
    public void handle() {
        db.connectToDataBase();
        db.changeName(id, name);
        db.closeDataBase();
        new File(ServerLoader.getRoot().getAbsolutePath()+ "\\" + id + ".zip").delete(); //удаляем старый))))
        ZIP.toZIP(file, ServerLoader.getRoot().getAbsolutePath()+ "\\" + id + ".zip");
    }
}

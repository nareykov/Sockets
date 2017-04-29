package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.ZIP;

import java.io.*;
import java.util.Scanner;

/**
 * Сообщение о создании нового архива
 */
public class PacketCreateCase extends OPacket {

    File file;
    String name;

    private static int id = 1;

    DataBase db = new DataBase();

    public PacketCreateCase() {

    }

    @Override
    public short getId() {
        return 6;
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
    }

    @Override
    public void handle() {
        File idFile = new File("idFile");
        try {
            Scanner scanner = new Scanner(idFile);
            id = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        db.connectToDataBase();
        db.insertIntoFileBase(id, name);
        db.closeDataBase();
        ZIP.toZIP(file, ServerLoader.getRoot().getAbsolutePath()+ "\\" + id + ".zip");

        id++;
        try {
            PrintWriter out = new PrintWriter(idFile.getAbsoluteFile());
            out.print(id);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

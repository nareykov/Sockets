package source.packet;

import source.DataBase;
import source.ServerLoader;

import java.io.*;
import java.util.Scanner;

/**
 * Created by narey on 25.04.2017.
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
        if (idFile.exists()) {
            try {
                Scanner scanner = new Scanner(idFile);
                id = scanner.nextInt();
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                idFile.createNewFile();
                PrintWriter out = new PrintWriter(idFile.getAbsoluteFile());
                out.print(1);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            db.connectToDataBase();
            db.insertIntoFileBase(id, name);
            file.renameTo(new File(ServerLoader.getRoot().getAbsolutePath()+ "\\" + id + ".xml"));
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

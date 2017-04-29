package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.DatabaseTableModel;

import java.io.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Сообщение загрузки содержания таблицы админа
 */
public class PacketLoadAdminTable extends OPacket {


    DatabaseTableModel model;

    DataBase db = new DataBase();

    private int typeOfParser;

    public PacketLoadAdminTable() {

    }

    @Override
    public short getId() {
        return 3;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeObject(model);
        oos.writeInt(typeOfParser);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {
        db.connectToDataBase();
        model = new DatabaseTableModel();
        try {
            model.setDataSource(db.getUsersResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        db.closeDataBase();

        File typeParserFile = new File("typeParserFile");
        try {
            Scanner scanner = new Scanner(typeParserFile);
            typeOfParser = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

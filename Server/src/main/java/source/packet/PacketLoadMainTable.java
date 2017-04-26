package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.DatabaseTableModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

/**
 * Created by narey on 25.04.2017.
 */
public class PacketLoadMainTable extends OPacket {

    DatabaseTableModel model;

    DataBase db = new DataBase();

    public PacketLoadMainTable() {

    }

    public PacketLoadMainTable(DatabaseTableModel model) {
        this.model = model;
    }

    @Override
    public short getId() {
        return 5;
    }

    @Override
    public void write(ObjectOutputStream oos) throws IOException {
        oos.writeObject(model);
    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {

    }

    @Override
    public void handle() {
        db.connectToDataBase();
        model = new DatabaseTableModel();
        try {
            model.setDataSource(db.getFileBaseResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        db.closeDataBase();
    }
}

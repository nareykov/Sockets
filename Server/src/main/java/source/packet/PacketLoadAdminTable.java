package source.packet;

import source.DataBase;
import source.ServerLoader;
import source.classes.AdminTableModel;
import source.classes.DatabaseTableModel;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by narey on 24.04.2017.
 */
public class PacketLoadAdminTable extends OPacket {


    DatabaseTableModel model;

    DataBase db = new DataBase();

    PacketLoadAdminTable() {

    }

    PacketLoadAdminTable(DatabaseTableModel model) {
        this.model = model;
    }

    @Override
    public short getId() {
        return 3;
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
            model.setDataSource(db.getUsersResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ServerLoader.sendPacket(socket, new PacketLoadAdminTable(model));
        db.closeDataBase();
    }
}

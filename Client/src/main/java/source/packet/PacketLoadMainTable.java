package source.packet;

import source.classes.DatabaseTableModel;
import source.gui.Main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by narey on 25.04.2017.
 */
public class PacketLoadMainTable extends OPacket {

    DatabaseTableModel model;

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

    }

    @Override
    public void read(ObjectInputStream ois) throws IOException {
        try {
            model = (DatabaseTableModel) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle() {
        final JTable table = Main.getMainWindow().getTable();
        table.setModel(model);
    }
}

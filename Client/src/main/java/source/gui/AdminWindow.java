package source.gui;

import source.packet.PacketLoadAdminTable;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by narey on 12.04.2017.
 */
public class AdminWindow {

    private JTable table = new JTable(3, 3);

    AdminWindow() {
        final JFrame frame = new JFrame("Admin");
        frame.setSize(515, 470);
        frame.addWindowListener(new MyWindowListener());
        frame.setLocation(400,100);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        ImageIcon backImg = new ImageIcon("Client\\src\\main\\resources\\S017.png");

        JButton backBtn = new JButton(new ImageIcon(backImg.getImage().getScaledInstance(30, 30, backImg.getImage().SCALE_DEFAULT)));
        backBtn.setBorderPainted(false);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main.setEnterWindow(new EnterWindow());
            }
        });
        toolBar.add(backBtn);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(toolBar);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
}

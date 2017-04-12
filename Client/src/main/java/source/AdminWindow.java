package source;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Created by narey on 12.04.2017.
 */
public class AdminWindow {
    AdminWindow() {
        JFrame frame = new JFrame("Admin");
        frame.setSize(515, 470);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,100);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        ImageIcon backImg = new ImageIcon("Client\\src\\main\\resources\\S017.png");

        JButton backBtn = new JButton(new ImageIcon(backImg.getImage().getScaledInstance(30, 30, backImg.getImage().SCALE_DEFAULT)));
        backBtn.setBorderPainted(false);
        toolBar.add(backBtn);

        String[] headers = {"id", "Username", "lol"};
        Object[][] data = {
                {"d" , "dasfg", "fdsg"}
        };

        JTable table = new JTable(data, headers);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(0);
        column.setPreferredWidth(30);
        column = columnModel.getColumn(1);
        column.setPreferredWidth(250);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(toolBar);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}

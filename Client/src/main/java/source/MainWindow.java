package source;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Created by narey on 12.04.2017.
 */
public class MainWindow {
    MainWindow() {
        JFrame frame = new JFrame("Main");
        frame.setSize(515, 470);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(400,100);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        ImageIcon backImg = new ImageIcon("Client\\src\\main\\resources\\S017.png");
        ImageIcon caseImg = new ImageIcon("Client\\src\\main\\resources\\S085.png");
        ImageIcon delImg = new ImageIcon("Client\\src\\main\\resources\\S016.png");

        JButton backBtn = new JButton(new ImageIcon(backImg.getImage().getScaledInstance(30, 30, backImg.getImage().SCALE_DEFAULT)));
        backBtn.setBorderPainted(false);
        toolBar.add(backBtn);
        JButton caseBtn = new JButton(new ImageIcon(caseImg.getImage().getScaledInstance(30, 30, caseImg.getImage().SCALE_DEFAULT)));
        caseBtn.setBorderPainted(false);
        toolBar.add(caseBtn);
        JButton delBtn = new JButton(new ImageIcon(delImg.getImage().getScaledInstance(30, 30, delImg.getImage().SCALE_DEFAULT)));
        delBtn.setBorderPainted(false);
        toolBar.add(delBtn);

        String[] headers = {"id", "Surname Name Patronymic"};
        Object[][] data = {
                {"dsafd" , "dasfg"}
        };

        JTable table = new JTable(data, headers);

        TableColumnModel columnModel = table.getColumnModel();
        TableColumn column = columnModel.getColumn(0);
        column.setPreferredWidth(30);
        column = columnModel.getColumn(1);
        column.setPreferredWidth(350);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(toolBar);
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}

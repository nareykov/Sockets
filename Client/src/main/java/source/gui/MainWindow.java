package source.gui;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by narey on 12.04.2017.
 */
public class MainWindow {

    private JTable table = new JTable(3, 3);

    private int priority = 0;

    MainWindow(int priority) {
        this.priority = priority;
        System.out.println("Priority: " + priority);

        final JFrame frame = new JFrame("Main");
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

        JButton caseBtn = new JButton(new ImageIcon(caseImg.getImage().getScaledInstance(30, 30, caseImg.getImage().SCALE_DEFAULT)));
        caseBtn.setBorderPainted(false);
        caseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.setCreateCaseWindow(new CreateCaseWindow());
            }
        });
        toolBar.add(caseBtn);
        JButton delBtn = new JButton(new ImageIcon(delImg.getImage().getScaledInstance(30, 30, delImg.getImage().SCALE_DEFAULT)));
        delBtn.setBorderPainted(false);
        toolBar.add(delBtn);
        if (priority < 2) {
            caseBtn.setVisible(false);
            delBtn.setVisible(false);
        }
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

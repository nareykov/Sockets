package source.gui;

import source.packet.PacketDeleteCase;
import source.packet.PacketLoadMainTable;
import source.packet.PacketOpenCase;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Главное окно юзера
 */
public class MainWindow {

    private JTable table = new JTable(3, 3);

    private int priority = 0;

    MainWindow(int priority) {
        this.priority = priority;

        final JFrame frame = new JFrame("Main");
        frame.setSize(515, 470);
        frame.addWindowListener(new MyWindowListener());
        frame.setLocation(400,100);
        frame.setResizable(false);

        frame.setLayout(new FlowLayout());

        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        toolBar.setFloatable(false);

        ImageIcon refreshImg = new ImageIcon("Client\\src\\main\\resources\\S015.png");
        ImageIcon backImg = new ImageIcon("Client\\src\\main\\resources\\S017.png");
        ImageIcon caseImg = new ImageIcon("Client\\src\\main\\resources\\S085.png");
        ImageIcon delImg = new ImageIcon("Client\\src\\main\\resources\\S016.png");

        JButton refreshBtn = new JButton(new ImageIcon(refreshImg.getImage().getScaledInstance(30, 30, refreshImg.getImage().SCALE_DEFAULT)));
        refreshBtn.setBorderPainted(false);
        refreshBtn.setVisible(true);
        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sendPacket(new PacketLoadMainTable());
            }
        });
        toolBar.add(refreshBtn);
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
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = table.getSelectionModel().getLeadSelectionIndex();
                String id = (String) table.getModel().getValueAt(index, 0);
                System.out.println("id: " + id);
                Main.sendPacket(new PacketDeleteCase(Integer.parseInt(id)));
                Main.sendPacket(new PacketLoadMainTable());
            }
        });
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

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    int index = table.getSelectionModel().getLeadSelectionIndex();
                    String id = (String) table.getModel().getValueAt(index, 0);
                    System.out.println("id: " + id);
                    Main.sendPacket(new PacketOpenCase(Integer.parseInt(id)));
                }
            }
        });

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

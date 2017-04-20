package source.gui;

import source.classes.User;
import source.packet.PacketRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by narey on 11.04.2017.
 */
public class EnterWindow {
    EnterWindow() {
        JFrame frame = new JFrame("Enter");
        frame.setSize(250, 200);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent event) {
                Main.end();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        frame.setLocation(500,250);
        frame.setResizable(false);

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        frame.setLayout(grid);

        JLabel usernameLbl = new JLabel("Username:");
        JLabel passwordLbl = new JLabel("Password:");
        JLabel errorLbl = new JLabel("(Error message)");

        Dimension dimensionText = new Dimension(100,25);
        final JTextField username = new JTextField();
        username.setPreferredSize(dimensionText);
        final JTextField password = new JTextField();
        password.setPreferredSize(dimensionText);

        Dimension dimensionBtn = new Dimension(100,25);
        JButton regBtn = new JButton("Register");
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sendPacket(new PacketRegister(new User(username.getText(), password.getText())));
            }
        });
        regBtn.setPreferredSize(dimensionBtn);
        JButton enterBtn = new JButton("Enter");
        enterBtn.setPreferredSize(dimensionBtn);

        cons.weightx = 1.0;

        cons.insets = new Insets(10, 0, 0, 5);

        cons.gridx = 0;
        cons.gridy = 0;
        grid.setConstraints(usernameLbl, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        grid.setConstraints(passwordLbl, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        grid.setConstraints(username, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        grid.setConstraints(password, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        grid.setConstraints(regBtn, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        grid.setConstraints(enterBtn, cons);

        cons.gridwidth = GridBagConstraints.CENTER;
        cons.gridx = 0;
        cons.gridy = 3;
        grid.setConstraints(errorLbl, cons);

        frame.add(usernameLbl);
        frame.add(passwordLbl);
        frame.add(username);
        frame.add(password);
        frame.add(regBtn);
        frame.add(enterBtn);
        frame.add(errorLbl);

        frame.setVisible(true);
    }
}

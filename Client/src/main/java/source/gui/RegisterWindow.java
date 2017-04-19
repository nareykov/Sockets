package source.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by narey on 11.04.2017.
 */
public class RegisterWindow {
    RegisterWindow() {
        JFrame frame = new JFrame("Register");
        frame.setSize(250, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,250);
        frame.setResizable(false);

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        frame.setLayout(grid);

        JLabel usernameLbl = new JLabel("Username:");
        JLabel passwordLbl = new JLabel("Password:");
        JLabel confirmLbl = new JLabel("Confirm:");
        JLabel errorLbl = new JLabel("(Error message)");

        Dimension dimensionText = new Dimension(100,25);
        JTextField username = new JTextField();
        username.setPreferredSize(dimensionText);
        JTextField password = new JTextField();
        password.setPreferredSize(dimensionText);
        JTextField confirm = new JTextField();
        confirm.setPreferredSize(dimensionText);

        Dimension dimensionBtn = new Dimension(100,25);
        JButton regBtn = new JButton("Register");
        regBtn.setPreferredSize(dimensionBtn);

        cons.weightx = 1.0;

        cons.insets = new Insets(10, 0, 0, 5);

        cons.gridx = 0;
        cons.gridy = 0;
        grid.setConstraints(usernameLbl, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        grid.setConstraints(passwordLbl, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        grid.setConstraints(confirmLbl, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        grid.setConstraints(username, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        grid.setConstraints(password, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        grid.setConstraints(confirm, cons);

        cons.gridx = 1;
        cons.gridy = 3;
        grid.setConstraints(regBtn, cons);


        cons.gridwidth = GridBagConstraints.CENTER;
        cons.gridx = 0;
        cons.gridy = 4;
        grid.setConstraints(errorLbl, cons);

        frame.add(usernameLbl);
        frame.add(passwordLbl);
        frame.add(confirmLbl);
        frame.add(username);
        frame.add(password);
        frame.add(confirm);
        frame.add(regBtn);
        frame.add(errorLbl);

        frame.setVisible(true);
    }
}

package source;

import javax.swing.*;
import java.awt.*;

/**
 * Created by narey on 11.04.2017.
 */
public class CreateCaseWindow {
    CreateCaseWindow() {
        JFrame frame = new JFrame("Case");
        frame.setSize(250, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500,250);
        frame.setResizable(false);

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints cons = new GridBagConstraints();
        frame.setLayout(grid);

        JLabel surnameLbl = new JLabel("Surname:");
        JLabel nameLbl = new JLabel("Name:");
        JLabel patronymicLbl = new JLabel("Patronymic:");
        JLabel phoneLbl = new JLabel("Phone:");
        JLabel jobLbl = new JLabel("Job:");
        JLabel errorLbl = new JLabel("(Error message)");

        Dimension dimensionText = new Dimension(100,25);
        JTextField surname = new JTextField();
        surname.setPreferredSize(dimensionText);
        JTextField name = new JTextField();
        name.setPreferredSize(dimensionText);
        JTextField patronymic = new JTextField();
        patronymic.setPreferredSize(dimensionText);
        JTextField phone = new JTextField();
        phone.setPreferredSize(dimensionText);
        JTextField job = new JTextField();
        job.setPreferredSize(dimensionText);

        Dimension dimensionBtn = new Dimension(100,25);
        JButton saveBtn = new JButton("Save");
        saveBtn.setPreferredSize(dimensionBtn);

        cons.weightx = 1.0;

        cons.insets = new Insets(10, 0, 0, 5);

        cons.gridx = 0;
        cons.gridy = 0;
        grid.setConstraints(surnameLbl, cons);

        cons.gridx = 0;
        cons.gridy = 1;
        grid.setConstraints(nameLbl, cons);

        cons.gridx = 0;
        cons.gridy = 2;
        grid.setConstraints(patronymicLbl, cons);

        cons.gridx = 0;
        cons.gridy = 3;
        grid.setConstraints(phoneLbl, cons);

        cons.gridx = 0;
        cons.gridy = 4;
        grid.setConstraints(jobLbl, cons);

        cons.gridx = 1;
        cons.gridy = 0;
        grid.setConstraints(surname, cons);

        cons.gridx = 1;
        cons.gridy = 1;
        grid.setConstraints(name, cons);

        cons.gridx = 1;
        cons.gridy = 2;
        grid.setConstraints(patronymic, cons);

        cons.gridx = 1;
        cons.gridy = 3;
        grid.setConstraints(phone, cons);

        cons.gridx = 1;
        cons.gridy = 4;
        grid.setConstraints(job, cons);

        cons.gridx = 1;
        cons.gridy = 5;
        grid.setConstraints(saveBtn, cons);


        cons.gridwidth = GridBagConstraints.CENTER;
        cons.gridx = 0;
        cons.gridy = 6;
        grid.setConstraints(errorLbl, cons);

        frame.add(surnameLbl);
        frame.add(nameLbl);
        frame.add(patronymicLbl);
        frame.add(phoneLbl);
        frame.add(jobLbl);
        frame.add(surname);
        frame.add(name);
        frame.add(patronymic);
        frame.add(phone);
        frame.add(job);
        frame.add(saveBtn);
        frame.add(errorLbl);

        frame.setVisible(true);
    }
}
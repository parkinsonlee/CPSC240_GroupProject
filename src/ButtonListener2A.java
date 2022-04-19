package src;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ButtonListener2A implements ActionListener{
    private int year;
    private JLabel label;

    public ButtonListener2A(int input, JLabel label) {
        year = input;
        this.label = label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.subtractYear();
        label.setText(String.valueOf(Main.findYear()));
        Main.plot();
    }
}
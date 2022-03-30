package src;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener2 implements ActionListener{
    private int year;
    private JLabel label;

    public ButtonListener2(int input, JLabel label) {
        year = input;
        this.label = label;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Main.addYear();
        label.setText(String.valueOf(Main.getYear()));
    }
}

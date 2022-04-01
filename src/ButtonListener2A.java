package family.familly;

import javax.swing.*;
import java.awt.event.*;

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
        label.setText(String.valueOf(Main.getYear()));
    }
}
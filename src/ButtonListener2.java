package src;

import javax.swing.*;
import java.awt.event.*;

public class ButtonListener2 implements ActionListener{
    private int year;

    public ButtonListener2(int input) {
        input = year * -1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = Main.frame;
        String string = Integer.toString(year);

        year = year - 1;

        JLabel print = new JLabel(string + " B.C.");
        frame.getContentPane().add(print);

        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

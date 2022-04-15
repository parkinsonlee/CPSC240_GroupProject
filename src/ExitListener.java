package src;
import java.awt.event.*;
//For exit button in File ->
class ExitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
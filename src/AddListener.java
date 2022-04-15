package src;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

class ButtonListener implements ActionListener{
    // each button listener stores the name of the button
    private final JTextField text;
    private final JFrame f;

    public ButtonListener(JTextField textfield, JFrame frame) {
        text = textfield;
        f = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = text.getText();
        try {
            Main.download(input);
            JOptionPane.showMessageDialog(null, "Successfully retrieved URL. Please restart to load.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error downloading or finding URL.");
        }
        f.dispose();
    }
}
class AddListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        JTextField textfield = new JTextField();
        f.getContentPane().add(textfield);

        JButton button = new JButton("Download");
        button.addActionListener(new ButtonListener(textfield, f));
        f.getContentPane().add(button);

        f.pack();
        f.setVisible(true);
    }
}
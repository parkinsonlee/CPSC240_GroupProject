package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

class ButtonListenerB implements ActionListener{
    // each button listener stores the name of the button
    private final JTextField title1;
    private final JTextField desc;
    private final JTextField loc;
    private final JTextField d;
    private final JFrame frame;

    public ButtonListenerB(JTextField t, JTextField description, JTextField location, JTextField date, JFrame f) {
        title1 = t;
        desc = description;
        loc = location;
        d = date;
        frame = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String title = title1.getText();
        String description = desc.getText();
        String location = loc.getText();
        LocalDate date = LocalDate.parse(d.getText());

        File file = new File("wikis/" + title + ".html");
        boolean stop = false;
        if (file.exists()) {
            JOptionPane.showMessageDialog(null, "Error wiki with exact name already exists");
            stop = true;
        }
        if (!stop) {
            try {
                FileWriter myWriter = new FileWriter("wikis/" + title + ".html");

                myWriter.write("<title>" + title + " - Wikipedia</title>" + "<table class=\"infobox vevent\"" + "class=\"infobox-label\">Date" + date + "<th scope=\"row\" class=\"infobox-label\">"  + "</th><td class=\"infobox-data location\">"  + location  + "</tbody></table>");

                myWriter.close();
                JOptionPane.showMessageDialog(null, "Successfully added location. Please restart to load.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding location");
            }
        }
        frame.dispose();
    }
}
class CreateListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        JTextField t = new JTextField("Title");
        JTextField d = new JTextField("Description");
        JTextField l = new JTextField("Location");
        JTextField date = new JTextField("Date (XXXX-XX-XX)");
        t.setForeground(Color.GRAY);
        d.setForeground(Color.GRAY);
        l.setForeground(Color.GRAY);
        date.setForeground(Color.GRAY);


        t.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (t.getText().equals("Title")) {
                    t.setText("");
                    t.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (t.getText().equals("")) {
                    t.setText("Title");
                    t.setForeground(Color.GRAY);
                }
            }
        });
        d.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (d.getText().equals("Description")) {
                    d.setText("");
                    d.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (d.getText().equals("")) {
                    d.setText("Description");
                    d.setForeground(Color.GRAY);
                }
            }
        });
        l.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (l.getText().equals("Location")) {
                    l.setText("");
                    l.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (l.getText().equals("")) {
                    l.setText("Location");
                    l.setForeground(Color.GRAY);
                }
            }
        });
        date.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (date.getText().equals("Date (XXXX-XX-XX)")) {
                    date.setText("");
                    date.setForeground(Color.black);
                }
            }

            public void focusLost(FocusEvent e) {
                if (date.getText().equals("")) {
                    date.setText("Date (XXXX-XX-XX)");
                    date.setForeground(Color.GRAY);
                }
            }
        });


        f.getContentPane().add(t);
        f.getContentPane().add(d);
        f.getContentPane().add(l);
        f.getContentPane().add(date);

        JButton button = new JButton("Add");
        button.addActionListener(new ButtonListenerB(t, d, l, date, f));
        f.getContentPane().add(button);

        f.pack();
        f.setVisible(true);
    }
}
package src;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

class ButtonListenerA implements ActionListener{
    // each button listener stores the name of the button
    private JTextField y;
    private JTextField x;
    private JTextField city1;
    private JTextField state1;
    private JTextField country1;
    private JFrame frame;

    public ButtonListenerA(JTextField ycoord, JTextField xcoord, JTextField city, JTextField state, JTextField country, JFrame f) {
        y = ycoord;
        x = xcoord;
        city1 = city;
        state1 = state;
        country1 = country;
        frame = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double ycoord = Double.parseDouble(y.getText());
        double xcoord = Double.parseDouble(x.getText());
        String city = city1.getText();
        String state = state1.getText();
        String country = country1.getText();
        try {
            FileWriter myWriter = new FileWriter("wikis/city_database.csv", true);
            myWriter.write(city + "," + state + "," + country + "," + xcoord + "," + ycoord);
            myWriter.close();
            JOptionPane.showMessageDialog(null, "Successfully added location. Please restart to load.");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding location");
        }
        frame.dispose();
    }
}
class AddLocationListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        JFrame f = new JFrame();
        f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
        JTextField city = new JTextField("City");
        JTextField state = new JTextField("State");
        JTextField country = new JTextField("Country");
        JTextField xcoord = new JTextField("Latitude");
        JTextField ycoord = new JTextField("Longitude");


       city.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (city.getText().equals("City")) {
                    city.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (city.getText().equals("")) {
                    city.setText("City");
                }
            }
        });
        state.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (state.getText().equals("State")) {
                    state.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (state.getText().equals("")) {
                    state.setText("State");
                }
            }
        });
        country.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (country.getText().equals("Country")) {
                    country.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (country.getText().equals("")) {
                    country.setText("Country");
                }
            }
        });
        xcoord.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (xcoord.getText().equals("Latitude")) {
                    xcoord.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (xcoord.getText().equals("")) {
                    xcoord.setText("Latitude");
                }
            }
        });
        ycoord.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (ycoord.getText().equals("Longitude")) {
                    ycoord.setText("");
                }
            }

            public void focusLost(FocusEvent e) {
                if (ycoord.getText().equals("")) {
                    ycoord.setText("Longitude");
                }
            }
        });

        f.getContentPane().add(city);
        f.getContentPane().add(state);
        f.getContentPane().add(country);
        f.getContentPane().add(xcoord);
        f.getContentPane().add(ycoord);

        JButton button = new JButton("Add");
        button.addActionListener(new ButtonListenerA(ycoord, xcoord, city, state, country, f));
        f.getContentPane().add(button);

        f.pack();
        f.setVisible(true);
    }
}
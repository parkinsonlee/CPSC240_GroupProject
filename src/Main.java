package src;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static ArrayList<Wiki> wikis = new ArrayList<>();

    public static void main(String[] args) {
        //lines 15-48 read files in wiki directory and retrieve relevant information
        File deckdir = new File("wikis/");
        deckdir.mkdir();
        File[] files = deckdir.listFiles();
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(".html")) {
                FileReader reader;
                try {
                    reader = new FileReader("wikis\\" + f.getName());
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to recognize file name.");
                    return;
                }
                Scanner in = new Scanner(reader);
                while (in.hasNext()) {
                    try {
                        while (in.hasNext()) {
                            //probably should read whole fill as string as should have following read from this string instead of reading the file everytime
                            String title = null;//write method to find title
                            String description = null;//write method to find description
                            String location = null;//write method to find location
                            LocalDate date = null;//write method to find date
                            Wiki w = new Wiki(title, description, location, date);
                            wikis.add(w);
                            try {
                                in.nextLine();
                            } catch (Exception ignore) {
                            }
                        }
                    } catch (InputMismatchException e) {
                        in.next();
                    }
                }
            }
        }

        JFrame frame = new JFrame("Wiki Plots");

        // make the program close when the window closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the box layout
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // display the window.
        frame.pack();
        frame.setVisible(true);



    }
}
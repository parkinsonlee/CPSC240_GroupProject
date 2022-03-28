package src;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
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
                String content = "";
                while (in.hasNext()) {
                    try {
                        while (in.hasNext()) {
                            content += (in.nextLine());
                        }
                    } catch (InputMismatchException e) {
                        in.next();
                    }
                    String s1 = content.substring(content.indexOf("<title>"));
                    s1 = s1.split(" - Wikipedia</title>")[0];
                    s1 = s1.replace("<title>", "");
                    String title = s1;//write method to find title
                    String description = null;//write method to find description
                    String location = null;//write method to find location
                    LocalDate date = null;//write method to find date
                    Wiki w = new Wiki(title, description, location, date);
                    wikis.add(w);
                    //System.out.println(content);
                    System.out.println(title);
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
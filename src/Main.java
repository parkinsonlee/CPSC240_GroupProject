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

        File directory = new File("wikis/");
        directory.mkdir();
        File[] files = directory.listFiles();

        //read files in wiki directory and retrieves relevant information
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
                    //get title
                    String title = content.substring(content.indexOf("<title>"));
                    title = title.split(" - Wikipedia</title>")[0];
                    title = title.replace("<title>", "");

                    String description = null;//write method to find location

                    //Rest of relevant information will be in info box to shorten content to make more efficient
                    String infobox = content.substring(content.indexOf("<table class=\"infobox vevent\""));
                    infobox = infobox.split("</tbody></table>")[0];
                    infobox = infobox.replace("<table class=\"infobox vevent\"", "");

                    //get location
                    String location = infobox.substring(infobox.indexOf("</th><td class=\"infobox-data location"));
                    location = location.split("<th scope=\"row\" class=\"infobox-label\">")[0];
                    location = location.replace("</th><td class=\"infobox-data location\">", "");
                    location = location.replaceAll("(<sup)(.*?)(</sup>)","$1$3");
                    location = location.replace("<sup</sup>", "");
                    location = location.replaceAll("(<)(.*?)(>)","$1$3");
                    location = location.replace("<>", "");

                    //get date
                    String date;
                    LocalDate localdate = null;
                    try {
                        date = infobox.substring(infobox.indexOf("class=\"infobox-label\">Date"));
                        date = date.split("<th scope=\"row\" class=\"infobox-label\">")[0];
                        date = date.replace("class=\"infobox-label\">Date", "");
                        date = date.replaceAll("(<sup)(.*?)(</sup>)", "$1$3");
                        date = date.replace("<sup</sup>", "");
                        date = date.replaceAll("(<)(.*?)(>)", "$1$3");
                        date = date.replace("<>", "");
                        date = date.split("\\(")[1];
                        date = date.split("\\)")[0];
                        localdate = LocalDate.parse(date);
                    } catch (Exception e) {
                        date = null;
                    }

                    Wiki w = new Wiki(title, description, location, localdate);
                    wikis.add(w);

                    System.out.println(title);
                    System.out.println(location);
                    System.out.println(date);
                    System.out.println();
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
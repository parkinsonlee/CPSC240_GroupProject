package src;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static JFrame frame = new JFrame("Wiki Plots");
    static ArrayList<Wiki> wikis = new ArrayList<>();
    static ArrayList<Location> locations = new ArrayList<>();
    public static int year = 2000;

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
                }
            }
        }
        System.out.println("Succesfully loaded " + wikis.size() + " wikis");

        for (File f : files) {
            if (f.isFile() && f.getName().equals("city_database.csv")) {
                FileReader reader;
                try {
                    reader = new FileReader("wikis\\city_database.csv");
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to retrieve city database.");
                    return;
                }
                Scanner in = new Scanner(reader);
                while (in.hasNext()) {
                    try {
                        String line = (in.nextLine());
                        String city = line.split(",")[0];
                        String state = line.split(",")[1];
                        String country = line.split(",")[2];
                        double xcoords;
                        double ycoords;
                        try {
                            xcoords = Double.parseDouble(line.split(",")[3]);
                            ycoords = Double.parseDouble(line.split(",")[4]);
                        } catch (Exception ignored) {
                            break;
                        }
                        Location l = new Location(city, state, country, xcoords, ycoords);
                        locations.add(l);
                    } catch (InputMismatchException e) {
                        in.next();
                    }
                }
            }
        }
        System.out.println("Loaded " + locations.size() + " places");

        frame.getContentPane().setLayout(new FlowLayout());
        JLabel label = new JLabel(String.valueOf(year));

        addButtonDown("<", frame, label);
        frame.getContentPane().add(label);
        addButtonUp(">", frame, label);

        JLabel imgLabel = new JLabel(new ImageIcon("world-map-29700.jpg"));
        frame.getContentPane().add(imgLabel);
        frame.pack();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void addButtonUp(String text, JFrame f, JLabel label) {
        // add a button object
        int year = getYear();
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener2(year, label));
        f.getContentPane().add(button);
    }

    public static int getYear() {
        return year;
    }
    public static void addYear(){
        year++;
    }
    public static void subtractYear(){
        year--;
    }


    public static void addButtonDown(String text, JFrame f, JLabel label) {
        // add a button object
        int year = getYear();
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener2A(year, label));
        f.getContentPane().add(button);
    }

}
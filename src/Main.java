package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


//Need to plot points on map
//Need to pretty up code

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
                StringBuilder content = new StringBuilder();
                while (in.hasNext()) {
                    try {
                        while (in.hasNext()) {
                            content.append(in.nextLine());
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

                    int count = 0;
                    char semi = ';';
                    for (int i = 0; i < location.length(); i++) {
                        if (location.charAt(i) == semi) {
                            count++;
                        }
                    }
                    ArrayList<String> locations = new ArrayList<>(Arrays.asList(location.split(";")).subList(0, count + 1));

                    //get date
                    String date;
                    LocalDate localdate = LocalDate.of(0,1,1);
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
                    } catch (Exception ignored) {}

                    Wiki w = new Wiki(title, description, locations, localdate);
                    wikis.add(w);
                }
            }
        }

        System.out.println("Successfully loaded " + wikis.size() + " wikis");

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
                        if (!state.isBlank()) {
                            city += ", " + state + ", " + country;
                        }
                        else {
                            city += ", " + country;
                        }
                        double xcoords;
                        double ycoords;
                        try {
                            xcoords = Double.parseDouble(line.split(",")[3]);
                            ycoords = Double.parseDouble(line.split(",")[4]);
                        } catch (Exception ignored) {
                            break;
                        }
                        Location l = new Location(city, xcoords, ycoords);
                        locations.add(l);
                    } catch (InputMismatchException e) {
                        in.next();
                    }
                }
            }
        }
        System.out.println("Loaded " + locations.size() + " places");

        frame.getContentPane().setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(year));

        addButtonDown("<", frame, label);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        addButtonUp(">", frame, label);
        JLabel imgLabel = new JLabel(new ImageIcon("world-map-29700.jpg"));
        frame.getContentPane().add(imgLabel, BorderLayout.PAGE_END);
        // create the menu bar
        JMenuBar menubar = new JMenuBar();

        // add the file menu
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        // add a menu item
        JMenuItem add = new JMenuItem("Add Wiki", null);
        add.setMnemonic(KeyEvent.VK_E);
        add.setToolTipText("Add wiki to the program");

        JMenuItem addlocation = new JMenuItem("Add Location", null);
        addlocation.setMnemonic(KeyEvent.VK_E);
        addlocation.setToolTipText("Add location");

        JMenuItem exit = new JMenuItem("Exit", null);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit the program");

        // add the action as a new anonymous object
        add.addActionListener(new AddListener());
        file.add(addlocation);
        addlocation.addActionListener(new AddLocationListener());
        file.add(add);
        exit.addActionListener(new ExitListener());
        file.add(exit);

        // add file to the menubar
        menubar.add(file);

        // add the menubar to the window
        frame.setJMenuBar(menubar);

        // set other things
        frame.setTitle("Simple menu");
        frame.setSize(300, 200);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JOptionPane.showMessageDialog(null, "Successfully loaded " + wikis.size() + " wiki(s)");
    }
    public static void addButtonUp(String text, JFrame f, JLabel label) {
        // add a button object
        int year = findYear();
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener2(year, label));
        f.getContentPane().add(button, BorderLayout.LINE_END);
    }

    public static int findYear() {
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
        int year = findYear();
        JButton button = new JButton(text);
        button.addActionListener(new ButtonListener2A(year, label));
        f.getContentPane().add(button, BorderLayout.LINE_START);
    }
    public static void plot(){
        for (Wiki w: wikis){
            LocalDate date = w.getDate();
            if(date.getYear() == findYear()){
                w.list();
                for (String l : w.getLocation()){
                    System.out.println(findXCoord(l));
                    System.out.println(findYCoord(l));
                }
            }
        }
    }
    public static double findXCoord(String location){
        for (Location l : locations) {
            if (location.equals(l.getCity())) {
                return l.getXCoords();
            }
        }
        return 0;
    }
    public static double findYCoord(String location){
        for (Location l : locations) {
            if (location.equals(l.getCity())) {
                return l.getYCoords();
            }
        }
        return 0;
    }
    //Need to make it so that filename is unique everytime
    public static void download(String urlString) throws IOException {//Downloads webpage for given URL
        URL url = new URL(urlString);
        File directory = new File("wikis/");
        File[] files = directory.listFiles();
        int i = 0;
        for (File f : files) {
            if (f.isFile() && f.getName().endsWith(i+ ".html")) {
                i++;
            }
        }
        try(
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                BufferedWriter writer = new BufferedWriter(new FileWriter("wikis//"+ i + ".html"))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }
            System.out.println("Page downloaded.");
        }
    }
}
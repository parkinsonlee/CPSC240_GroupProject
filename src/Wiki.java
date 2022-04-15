package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Wiki {
    private final String title;
    private final String description;
    private final ArrayList<String> location;
    private LocalDate date;


    public Wiki(String title, String description, ArrayList<String> location, LocalDate date){
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
    }
    public LocalDate getDate(){
        return date;
    }
    public void list(){
        System.out.println();
        System.out.println(title);
        System.out.println(location);
        System.out.println(date);
    }
    public void getXCoord(){
        for (String i : location) {
            System.out.println(Main.findXCoord(i));
        }
    }
    public void getYCoord(){
        for (String i : location) {
            System.out.println(Main.findYCoord(i));
        };
    }
}
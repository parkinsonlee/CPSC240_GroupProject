package src;

import java.time.LocalDate;

public class Wiki {
    private final String title;
    private final String description;
    private final String location;
    private LocalDate date;


    public Wiki(String title, String description, String location, LocalDate date){
        this.title = title;
        this.description = description;
        this.location = location;
        this.date = date;
    }
}
package src;

public class Location {
    private final String city;
    private final String state;
    private final String country;
    private final double xcoords;
    private final double ycoords;


    public Location(String city, String state, String country, double xcoords, double ycoords){
        this.city = city;
        this.state = state;
        this.country = country;
        this.xcoords = xcoords;
        this.ycoords = ycoords;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return city;
    }
    public String getCountry(){
        return city;
    }
    public double getXCoords(){
        return xcoords;
    }
    public double getYCoords(){
        return xcoords;
    }
}
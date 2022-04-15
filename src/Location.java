package src;

public class Location {
    private final String city;
    private final double xcoords;
    private final double ycoords;


    public Location(String city, double xcoords, double ycoords){
        this.city = city;
        this.xcoords = xcoords;
        this.ycoords = ycoords;
    }
    public String getCity(){
        return city;
    }
    public double getXCoords(){
        return xcoords;
    }
    public double getYCoords(){
        return ycoords;
    }
}
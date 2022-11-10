package flightmanagementsystem.model;

public class City {
    private String cityName;
    private int milesFromDestination;
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getMilesFromDestination() {
        return milesFromDestination;
    }
    public void setMilesFromDestination(int milesFromDestination) {
        this.milesFromDestination = milesFromDestination;
    }
}
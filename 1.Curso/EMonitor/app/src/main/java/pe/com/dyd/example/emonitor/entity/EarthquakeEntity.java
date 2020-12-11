package pe.com.dyd.example.emonitor.entity;

public class EarthquakeEntity {
    private String magnitude;
    private String place;

    public EarthquakeEntity(String magnitude, String place) {
        this.magnitude = magnitude;
        this.place = place;
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

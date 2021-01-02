package pe.com.dyd.example.emonitor.entity;

public class EarthquakeEntity {
    private Double magnitude;
    private String place;

    public EarthquakeEntity(Double magnitude, String place) {
        this.magnitude = magnitude;
        this.place = place;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

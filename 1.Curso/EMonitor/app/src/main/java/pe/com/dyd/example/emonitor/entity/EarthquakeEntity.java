package pe.com.dyd.example.emonitor.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class EarthquakeEntity implements Parcelable {
    private Long dateTime;
    private Double magnitude;
    private String place;
    private String longitude;
    private String latitude;

    public EarthquakeEntity(Long dateTime, Double magnitude, String place, String longitude, String latitude) {
        this.dateTime = dateTime;
        this.magnitude = magnitude;
        this.place = place;
        this.longitude = longitude;
        this.latitude = latitude;
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

    protected EarthquakeEntity(Parcel in) {
        dateTime = in.readLong();
        magnitude = in.readByte() == 0x00 ? null : in.readDouble();
        place = in.readString();
        longitude = in.readString();
        latitude = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(dateTime);
        if (magnitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(magnitude);
        }
        dest.writeString(place);
        dest.writeString(longitude);
        dest.writeString(latitude);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<EarthquakeEntity> CREATOR = new Parcelable.Creator<EarthquakeEntity>() {
        @Override
        public EarthquakeEntity createFromParcel(Parcel in) {
            return new EarthquakeEntity(in);
        }

        @Override
        public EarthquakeEntity[] newArray(int size) {
            return new EarthquakeEntity[size];
        }
    };

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dateTime) {
        this.dateTime = dateTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
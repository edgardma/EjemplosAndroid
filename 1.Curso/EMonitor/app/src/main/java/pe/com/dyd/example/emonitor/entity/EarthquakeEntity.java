package pe.com.dyd.example.emonitor.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class EarthquakeEntity implements Parcelable {
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

    protected EarthquakeEntity(Parcel in) {
        magnitude = in.readByte() == 0x00 ? null : in.readDouble();
        place = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (magnitude == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(magnitude);
        }
        dest.writeString(place);
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
}
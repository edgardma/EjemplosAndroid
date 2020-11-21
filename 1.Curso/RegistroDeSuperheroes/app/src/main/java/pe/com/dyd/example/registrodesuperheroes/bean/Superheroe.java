package pe.com.dyd.example.registrodesuperheroes.bean;

import android.os.Parcel;
import android.os.Parcelable;

// Implementando el Parcelable desde http://www.parcelabler.com/
public class Superheroe implements Parcelable {
    private String name;
    private String lastName;
    private String heroName;
    private String age;
    private String address;
    private String city;

    public Superheroe(String name, String lastName, String heroName, String age, String address, String city) {
        this.name = name;
        this.lastName = lastName;
        this.heroName = heroName;
        this.age = age;
        this.address = address;
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    protected Superheroe(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        heroName = in.readString();
        age = in.readString();
        address = in.readString();
        city = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(heroName);
        dest.writeString(age);
        dest.writeString(address);
        dest.writeString(city);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Superheroe> CREATOR = new Parcelable.Creator<Superheroe>() {
        @Override
        public Superheroe createFromParcel(Parcel in) {
            return new Superheroe(in);
        }

        @Override
        public Superheroe[] newArray(int size) {
            return new Superheroe[size];
        }
    };
}

package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import play.db.jpa.Model;

@Entity
public class Station extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();

    public String weatherConditions;
    public double tempFarenheit;
    public double tempCelsius;
    public int pressure;
    public int beaufort;
    public String compassDirection;
    public double windChill;
    public String weatherIcon;
    public double latitude;
    public double longitude;
    public double maxWindSpeed;
    public double minWindSpeed;
    public int maxPressure;
    public int minPressure;
    public double maxTemp;
    public double minTemp;
    public String tempTrend;
    public String windTrend;
    public String pressureTrend;

    public Station(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

}
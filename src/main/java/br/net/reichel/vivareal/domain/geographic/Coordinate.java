package br.net.reichel.vivareal.domain.geographic;

import com.fasterxml.jackson.annotation.JsonSetter;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 *
 * (x, y) -> (longitude, latitude)
 */
public class Coordinate {

    public static final Integer LATITUDE_MAX = 1100;
    public static final Integer LONGITUDE_MAX = 1400;
    public static final Integer LATITUDE_MIN = 0;
    public static final Integer LONGITUDE_MIN = 0;

    private Integer latitude;
    private Integer longitude;

    public Coordinate() {
    }

    public Coordinate(String x, String y) {
        this(Integer.valueOf(y), Integer.valueOf(x));
    }

    public Coordinate(Coordinate reference) {
        this(reference.getLatitude(), reference.getLongitude());
    }

    public Coordinate(Integer latitude, Integer longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public Integer getLatitude() {
        return latitude;
    }

    @JsonSetter("y")
    public void setLatitude(Integer latitude) {
        checkArgument(latitude != null && latitude >= LATITUDE_MIN && latitude <= LATITUDE_MAX, "invalid latitude %s", latitude);
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    @JsonSetter("x")
    public void setLongitude(Integer longitude) {
        checkArgument(longitude != null && longitude >= LONGITUDE_MIN && longitude <= LONGITUDE_MAX, "invalid longitude %s", longitude);
        this.longitude = longitude;
    }

    public Integer getY() {
        return latitude;
    }

    public Integer getX() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitude(y)=" + latitude +
                ", longitude(x)=" + longitude +
                '}';
    }
}

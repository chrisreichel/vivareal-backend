package br.net.reichel.vivareal.domain.geographic;

import com.fasterxml.jackson.annotation.JsonSetter;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 *
 * (x, y) -> (latitude, longitude)
 */
public class Coordinate {

    private static final Integer LATITUDE_MAX = 1400;
    private static final Integer LONGITUDE_MAX = 1100;
    private static final Integer LATITUDE_MIN = 0;
    private static final Integer LONGITUDE_MIN = 0;

    private Integer latitude;
    private Integer longitude;

    public Coordinate() {
    }

    public Coordinate(String x, String y) {
        this(Integer.valueOf(x), Integer.valueOf(y));
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

    @JsonSetter("x")
    public void setLatitude(Integer latitude) {
        checkArgument(latitude != null && latitude >= LATITUDE_MIN && latitude <= LATITUDE_MAX, "invalid latitude %s", latitude);
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    @JsonSetter("y")
    public void setLongitude(Integer longitude) {
        checkArgument(longitude != null && longitude >= LONGITUDE_MIN && longitude <= LONGITUDE_MAX, "invalid longitude %s", longitude);
        this.longitude = longitude;
    }

    public Integer getY() {
        return longitude;
    }

    public Integer getX() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "latitude(x)=" + latitude +
                ", longitude(y)=" + longitude +
                '}';
    }
}

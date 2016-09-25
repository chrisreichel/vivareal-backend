package br.net.reichel.vivareal.domain.geographic;

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

    private final Integer latitude;
    private final Integer longitude;

    public Coordinate(String x, String y) {
        this(Integer.valueOf(y), Integer.valueOf(x));
    }

    public Coordinate(Integer latitude, Integer longitude) {
        checkArgument(latitude != null && latitude >= LATITUDE_MIN && latitude <= LATITUDE_MAX, "invalid latitude %s", latitude);
        checkArgument(longitude != null && longitude >= LONGITUDE_MIN && longitude <= LONGITUDE_MAX, "invalid longitude %s", longitude);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public Integer getLongitude() {
        return longitude;
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

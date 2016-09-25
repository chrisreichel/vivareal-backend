package br.net.reichel.vivareal.domain.geographic;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class Coordinate {

    private Integer latitude;
    private Integer longitude;

    public Coordinate(Integer latitude, Integer longitude) {
        checkArgument(latitude != null && latitude >= 0, "invalid latitude %s", latitude);
        checkArgument(longitude != null && longitude >= 0, "invalid longitude %s", longitude);
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

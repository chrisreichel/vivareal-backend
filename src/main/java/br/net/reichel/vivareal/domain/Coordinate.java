package br.net.reichel.vivareal.domain;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Tiny Type para coordenadas
 * Created by Christian Reichel on 9/25/2016.
 */
class Coordinate {

    private Integer latitude;
    private Integer longitude;

    Coordinate(Integer latitude, Integer longitude) {
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

    Integer getY() {
        return latitude;
    }

    Integer getX() {
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

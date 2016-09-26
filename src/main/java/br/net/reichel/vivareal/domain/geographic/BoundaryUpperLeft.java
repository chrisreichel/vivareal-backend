package br.net.reichel.vivareal.domain.geographic;

/**
 * Tiny Type
 * Created by Christian Reichel on 9/25/2016.
 */
public class BoundaryUpperLeft extends Coordinate {

    public BoundaryUpperLeft(Coordinate coordinate) {
        super(coordinate);
    }

    public BoundaryUpperLeft(Integer latitude, Integer longitude) {
        super(latitude, longitude);
    }

    public BoundaryUpperLeft(String latitude, String longitude) {
        super(latitude, longitude);
    }

    @Override
    public String toString() {
        return "BoundaryUpperLeft{" +
                "latitude(x)=" + super.getLatitude() +
                ", longitude(y)=" + super.getLongitude() +
                '}';
    }
}

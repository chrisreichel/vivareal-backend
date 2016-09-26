package br.net.reichel.vivareal.domain.geographic;

/**
 * Tiny Type
 * Created by Christian Reichel on 9/25/2016.
 */
public class BoundaryBottomRight extends Coordinate {

    public BoundaryBottomRight(Coordinate coordinate) {
        super(coordinate);
    }

    public BoundaryBottomRight(Integer latitude, Integer longitude) {
        super(latitude, longitude);
    }

    @Override
    public String toString() {
        return "BoundaryBottomRight{" +
                "latitude(x)=" + super.getLatitude() +
                ", longitude(y)=" + super.getLongitude() +
                '}';
    }
}

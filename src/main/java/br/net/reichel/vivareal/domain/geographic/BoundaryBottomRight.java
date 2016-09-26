package br.net.reichel.vivareal.domain.geographic;

/**
 * Tiny Type
 * Created by Christian Reichel on 9/25/2016.
 */
public class BoundaryBottomRight extends Coordinate {

    public BoundaryBottomRight(Coordinate coordinate) {
        super(coordinate);
    }

    public BoundaryBottomRight(Integer longitude, Integer latitude) {
        super(longitude, latitude);
    }

    @Override
    public String toString() {
        return "BoundaryBottomRight{" +
                "latitude(y)=" + super.getLatitude() +
                ", longitude(x)=" + super.getLongitude() +
                '}';
    }
}

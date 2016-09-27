package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class Province {

    private String name;

    private BoundaryUpperLeft boundaryUpperLeft;
    private BoundaryBottomRight boundaryBottomRight;

    private Coordinate boundaryBottomLeft;
    private Coordinate boundaryUpperRight;

    public Province(String name) {
        this.name = name;
    }

    public Province(String name, BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight) {
        this.name = name;
        boundaryUpperLeft = upperLeft;
        boundaryBottomRight = bottomRight;

        // Dado que as "províncias" são retangulares
        boundaryUpperRight = new Coordinate(boundaryBottomRight.getLatitude(), boundaryUpperLeft.getLongitude());
        boundaryBottomLeft = new Coordinate(boundaryUpperLeft.getLatitude(), boundaryBottomRight.getLongitude());
    }

    public boolean contains(Coordinate location) {
        checkArgument(location != null, "invalid coordinate " + location);
        return (isBetweenBoundLatitudes(location.getLatitude()) && isBetweenBoundLongitudes(location.getLongitude()));
    }

    boolean isBetweenBoundLongitudes(Integer longitude) {
        return (boundaryBottomLeft.getLongitude() <= longitude && longitude <= boundaryUpperLeft.getLongitude());
    }

    boolean isBetweenBoundLatitudes(Integer latitude) {
        return (boundaryBottomLeft.getLatitude() <= latitude && latitude <= boundaryBottomRight.getLatitude());
    }

    public String getName() {
        return name;
    }

    public BoundaryUpperLeft getBoundaryUpperLeft() {
        return boundaryUpperLeft;
    }

    public BoundaryBottomRight getBoundaryBottomRight() {
        return boundaryBottomRight;
    }

    public Coordinate getBoundaryBottomLeft() {
        return boundaryBottomLeft;
    }

    public Coordinate getBoundaryUpperRight() {
        return boundaryUpperRight;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", boundaryUpperLeft=" + boundaryUpperLeft +
                ", boundaryBottomRight=" + boundaryBottomRight +
                ", boundaryBottonLeft=" + boundaryBottomLeft +
                ", boundaryUpperRight=" + boundaryUpperRight +
                '}';
    }
}

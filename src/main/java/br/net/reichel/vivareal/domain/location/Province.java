package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class Province {

    private String name;

    private BoundaryUpperLeft boundaryUpperLeft;
    private BoundaryBottomRight boundaryBottomRight;

    private Coordinate boundaryBottomLeft;
    private Coordinate boundaryUpperRight;

    public Province(String name, BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight) {
        this.name = name;
        boundaryUpperLeft = upperLeft;
        boundaryBottomRight = bottomRight;

        // Dado que as "províncias" são retangulares
        boundaryUpperRight = new Coordinate(boundaryUpperLeft.getLatitude(), boundaryBottomRight.getLongitude());
        boundaryBottomLeft = new Coordinate(boundaryBottomRight.getLatitude(), boundaryUpperLeft.getLongitude());
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

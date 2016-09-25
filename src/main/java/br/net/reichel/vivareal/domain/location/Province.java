package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class Province {

    // (x, y) -> (longitude, latitude)
    private String name;

    private BoundaryUpperLeft boundaryUpperLeft;
    private BoundaryBottomRight boundaryBottomRight;

    private Coordinate boundaryBottonLeft;
    private Coordinate boundaryUpperRight;

    public Province(String name, BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight) {
        this.name = name;
        boundaryUpperLeft = upperLeft;
        boundaryBottomRight = bottomRight;

        // Dado que as "províncias" são retangulares
        boundaryUpperRight = new Coordinate(boundaryUpperLeft.getLatitude(), boundaryBottomRight.getLongitude());
        boundaryBottonLeft = new Coordinate(boundaryBottomRight.getLongitude(), boundaryUpperLeft.getLongitude());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BoundaryUpperLeft getBoundaryUpperLeft() {
        return boundaryUpperLeft;
    }

    public void setBoundaryUpperLeft(BoundaryUpperLeft boundaryUpperLeft) {
        this.boundaryUpperLeft = boundaryUpperLeft;
    }

    public BoundaryBottomRight getBoundaryBottomRight() {
        return boundaryBottomRight;
    }

    public void setBoundaryBottomRight(BoundaryBottomRight boundaryBottomRight) {
        this.boundaryBottomRight = boundaryBottomRight;
    }

    public Coordinate getBoundaryBottonLeft() {
        return boundaryBottonLeft;
    }

    public void setBoundaryBottonLeft(Coordinate boundaryBottonLeft) {
        this.boundaryBottonLeft = boundaryBottonLeft;
    }

    public Coordinate getBoundaryUpperRight() {
        return boundaryUpperRight;
    }

    public void setBoundaryUpperRight(Coordinate boundaryUpperRight) {
        this.boundaryUpperRight = boundaryUpperRight;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", boundaryUpperLeft=" + boundaryUpperLeft +
                ", boundaryBottomRight=" + boundaryBottomRight +
                ", boundaryBottonLeft=" + boundaryBottonLeft +
                ", boundaryUpperRight=" + boundaryUpperRight +
                '}';
    }
}

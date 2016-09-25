package br.net.reichel.vivareal.domain;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class Province {

    // (x, y) -> (longitude, latitude)
    private String name;
    private Integer upperLeftLatitude;
    private Integer upperLeftLongitude;
    private Integer bottonRightLatitude;
    private Integer bottonRightLongitude;

    private Integer upperRightLatitude;
    private Integer upperRightLongitude;
    private Integer bottonLeftLatitude;
    private Integer bottonLeftLongitude;


    public Province(String name, BoundaryUpperLeft upperLeft, BoundaryBottonRight bottonRight){
        this.name = name;
        this.upperLeftLatitude = upperLeft.getLatitude();
        this.upperLeftLongitude = upperLeft.getLongitude();
        this.bottonRightLatitude = bottonRight.getLatitude();
        this.bottonRightLongitude = bottonRight.getLongitude();

        // Dado que as "províncias" são retangulares
        this.upperRightLongitude = this.bottonRightLongitude;
        this.upperRightLatitude = this.upperLeftLatitude;
        this.bottonLeftLongitude = this.upperLeftLongitude;
        this.bottonLeftLatitude = this.bottonRightLatitude;
    }

    public String getName() {
        return name;
    }

    public Integer getUpperLeftLatitude() {
        return upperLeftLatitude;
    }

    public Integer getUpperLeftLongitude() {
        return upperLeftLongitude;
    }

    public Integer getBottonRightLatitude() {
        return bottonRightLatitude;
    }

    public Integer getBottonRightLongitude() {
        return bottonRightLongitude;
    }

    public Integer getUpperRightLatitude() {
        return upperRightLatitude;
    }

    public Integer getUpperRightLongitude() {
        return upperRightLongitude;
    }

    public Integer getBottonLeftLatitude() {
        return bottonLeftLatitude;
    }

    public Integer getBottonLeftLongitude() {
        return bottonLeftLongitude;
    }

    @Override
    public String toString() {
        return "Province{" +
                "name='" + name + '\'' +
                ", upperLeftLatitude(y)=" + upperLeftLatitude +
                ", upperLeftLongitude(x)=" + upperLeftLongitude +
                ", bottonRightLatitude(y)=" + bottonRightLatitude +
                ", bottonRightLongitude(x)=" + bottonRightLongitude +
                ", upperRightLatitude(y)=" + upperRightLatitude +
                ", upperRightLongitude(x)=" + upperRightLongitude +
                ", bottonLeftLatitude(y)=" + bottonLeftLatitude +
                ", bottonLeftLongitude(x)=" + bottonLeftLongitude +
                '}';
    }
}

package br.net.reichel.vivareal.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by creichel on 9/25/16.
 */
public class Property {

    private Long id;
    private String title;
    private Integer price;
    private String description;
    private Integer latitude;
    private Integer longitude;
    private Integer beds;
    private Integer baths;
    private Integer squareMeters;
    private Set<Province> provinces = new HashSet<>();

    public Set<Province> getProvinces() {
        return Collections.unmodifiableSet(provinces);
    }

    public void setProvinces(Set<Province> provinces) {
        this.provinces = provinces;
    }

    public void addProvince(Province province) {
        if(provinces == null){
            provinces = new HashSet<>();
        }
        this.provinces.add(province);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
    }
}

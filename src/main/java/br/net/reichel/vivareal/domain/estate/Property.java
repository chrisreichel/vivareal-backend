package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.geographic.Coordinate;
import br.net.reichel.vivareal.domain.location.Province;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
    private Coordinate location;
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

    public Coordinate getLocation() {
        return location;
    }

    public void setLocation(Coordinate location) {
        this.location = location;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

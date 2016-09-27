package br.net.reichel.vivareal.web.event;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import br.net.reichel.vivareal.domain.location.Province;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
//@XmlRootElement
@JsonIgnoreProperties({"location"})
@JsonPropertyOrder({"id", "title", "price", "description", "x", "y", "beds", "baths", "squareMeters", "provinces"})
public class WebProperty extends Property {

    private Integer x;
    private Integer y;

    public WebProperty() {
    }

    public WebProperty(Property property) {
        BeanUtils.copyProperties(property, this);
        if (property != null && property.getLocation() != null) {
            x = property.getLocation().getX();
            y = property.getLocation().getY();
        }
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @JsonGetter("provinces")
    public List<String> getProvincesAsList() {
        return getProvinces().stream().map(Province::getName).collect(toList());
    }

    public Property toProperty() {
        final Property property = new Property();
        BeanUtils.copyProperties(this, property, "location", "id", "provinces");
        property.setLocation(new Coordinate(x, y));
        return property;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

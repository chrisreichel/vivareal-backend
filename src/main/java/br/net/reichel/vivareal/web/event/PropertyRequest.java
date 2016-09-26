package br.net.reichel.vivareal.web.event;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@XmlRootElement
@JsonIgnoreProperties({"location", "id", "provinces"})
public class PropertyRequest extends Property {

    private String x;
    private String y;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Property toProperty() throws Exception {
        final Property property = new Property();
        BeanUtils.copyProperties(this, property, "location", "id", "provinces");
        property.setLocation(new Coordinate(y, x)); //lat and long
        return property;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

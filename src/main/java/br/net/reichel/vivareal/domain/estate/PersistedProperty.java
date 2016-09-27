package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.geographic.Coordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

/**
 * Created by ChristianReichel on 9/26/2016.
 */
public class PersistedProperty extends Property {

    @JsonProperty("lat")
    private Integer latitude;

    @JsonProperty("long")
    private Integer longitude;

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

    public Property toProperty() throws Exception {
        final Property property = new Property();
        BeanUtils.copyProperties(this, property, "location", "id", "provinces");
        property.setLocation(new Coordinate(latitude, longitude));
        return property;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

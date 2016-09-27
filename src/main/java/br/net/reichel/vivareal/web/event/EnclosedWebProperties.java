package br.net.reichel.vivareal.web.event;

import br.net.reichel.vivareal.domain.estate.Property;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static java.util.stream.Collectors.toList;

/**
 * Encapsula o modelo de domínio da Property[]
 * Created by ChristianReichel on 9/26/2016.
 */
@JsonPropertyOrder({"totalProperties", "properties"})
public class EnclosedWebProperties {

    private Collection<WebProperty> properties = new ArrayList<>();

    public EnclosedWebProperties() {
    }

    public EnclosedWebProperties(Set<Property> found) {
        if (found != null && !found.isEmpty()) {
            properties = found.stream().map(WebProperty::new).collect(toList());
        }
    }

    @JsonGetter("totalProperties")
    public Integer getTotalProperties() {
        return properties.size();
    }

    public Collection<WebProperty> getProperties() {
        return properties;
    }

    public void setProperties(Collection<WebProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

package br.net.reichel.vivareal.web;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.web.event.PropertyRequest;
import com.google.common.collect.Lists;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by creichel on 9/25/16.
 */
@RestController
@RequestMapping(value = "/{properties}")
public class PropertyController {

    @RequestMapping(method = RequestMethod.POST)
    public Property create(@RequestBody PropertyRequest property) throws Exception {
        return property.toProperty();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Property getProperty(@PathVariable Long id) {
        final Property prop = new Property();
        prop.setId(id);
        return prop;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Property> search(HttpRequest httpRequest) {
        return Lists.newArrayList(new Property());
    }

}

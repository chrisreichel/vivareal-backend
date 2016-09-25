package br.net.reichel.vivareal.web;

import br.net.reichel.vivareal.domain.Property;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by creichel on 9/25/16.
 */
@RestController
@RequestMapping(value = "/{properties}")
public class PropertiesController {

    @RequestMapping(method = RequestMethod.POST)
    public Property create(@Valid Property property) {
        return new Property();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Property getProperty(@PathVariable Long id) {
        return new Property();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Property> search(HttpRequest httpRequest) {
        return Collections.emptyList();
    }

}

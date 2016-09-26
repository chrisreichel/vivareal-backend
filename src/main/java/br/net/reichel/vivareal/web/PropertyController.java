package br.net.reichel.vivareal.web;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.service.PropertyService;
import br.net.reichel.vivareal.web.event.EnclosedWebProperties;
import br.net.reichel.vivareal.web.event.WebProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Created by creichel on 9/25/16.
 */
@RestController
@RequestMapping(value = "/{properties}")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(method = RequestMethod.POST)
    public WebProperty create(@RequestBody WebProperty webProperty) {
        final Property created = propertyService.create(webProperty.toProperty());
        return new WebProperty(created);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebProperty getProperty(@PathVariable Integer id) {
        final Property property = propertyService.findById(id);
        return new WebProperty(property);
    }

    @RequestMapping(method = RequestMethod.GET)
    public EnclosedWebProperties search(HttpServletRequest httpReq) {
        final Set<Property> found = propertyService.findByArea(getBoundaryUpperLeftFrom(httpReq), getBoundaryBottomRight(httpReq));
        return new EnclosedWebProperties(found);
    }

    //------------------------------------------------------------------------------------------------------------------

    BoundaryUpperLeft getBoundaryUpperLeftFrom(HttpServletRequest httpRequest) {
        return new BoundaryUpperLeft(httpRequest.getParameter("ax"), httpRequest.getParameter("ay"));
    }

    BoundaryBottomRight getBoundaryBottomRight(HttpServletRequest httpRequest) {
        return new BoundaryBottomRight(httpRequest.getParameter("bx"), httpRequest.getParameter("by"));
    }
}

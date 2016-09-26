package br.net.reichel.vivareal.web;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.service.PropertyService;
import br.net.reichel.vivareal.web.event.EnclosedWebProperties;
import br.net.reichel.vivareal.web.event.WebProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(PropertyController.class);

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(method = RequestMethod.POST)
    public WebProperty create(@RequestBody WebProperty webProperty) {
        LOG.debug("Will create " + webProperty);
        final Property created = propertyService.create(webProperty.toProperty());
        LOG.debug("created " + created);
        return new WebProperty(created);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public WebProperty getProperty(@PathVariable Integer id) {
        LOG.debug("Looking for WebProperty " + id);
        final Property property = propertyService.findById(id);
        LOG.debug("found " + property);
        return new WebProperty(property);
    }

    @RequestMapping(method = RequestMethod.GET)
    public EnclosedWebProperties search(HttpServletRequest httpReq) {
        final Set<Property> found = propertyService.findByArea(getBoundaryUpperLeftFrom(httpReq), getBoundaryBottomRight(httpReq));
        LOG.debug("Amount of properties found: " + found.size());
        if (LOG.isDebugEnabled()) {
            found.forEach(property -> LOG.debug("found: " + property));
        }
        return new EnclosedWebProperties(found);
    }

    //------------------------------------------------------------------------------------------------------------------

    private BoundaryUpperLeft getBoundaryUpperLeftFrom(HttpServletRequest httpRequest) {
        final BoundaryUpperLeft retVal = new BoundaryUpperLeft(httpRequest.getParameter("ax"), httpRequest.getParameter("ay"));
        LOG.debug("will query by BoundaryUpperLeft: " + retVal);
        return retVal;
    }

    private BoundaryBottomRight getBoundaryBottomRight(HttpServletRequest httpRequest) {
        final BoundaryBottomRight retVal = new BoundaryBottomRight(httpRequest.getParameter("bx"), httpRequest.getParameter("by"));
        LOG.debug("will query by BoundaryBottomRight: " + retVal);
        return retVal;
    }
}

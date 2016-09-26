package br.net.reichel.vivareal.service;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.estate.PropertyRepository;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.location.Province;
import br.net.reichel.vivareal.domain.location.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Service
public class PropertyService {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyService.class);

    private PropertyRepository propertyRepository;
    private ProvinceRepository provinceRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, ProvinceRepository provinceRepository) {
        LOG.debug("Using repositories: " + propertyRepository + " and " + provinceRepository);
        this.propertyRepository = propertyRepository;
        this.provinceRepository = provinceRepository;
    }

    public Property create(Property property) {
        checkArgument(isValidInfo(property), "property contains invalid data");
        final Set<Province> provinces = provinceRepository.findBy(property.getLocation());
        property.setProvinces(provinces);
        return propertyRepository.create(property);
    }

    private boolean isValidInfo(Property p) {
        checkArgument(p != null, "property is invalid");
        checkArgument(p.getBaths() >= 1 && p.getBaths() <= 4, "invalid no. of baths (%s)", p.getBaths());
        checkArgument(p.getBeds() >= 1 && p.getBeds() <= 5, "invalid no. of beds (%s)", p.getBeds());
        checkArgument(p.getSquareMeters() >= 20 && p.getSquareMeters() <= 240, "invalid sq. meters (%s)", p.getBeds());
        return true;
    }

    public Property findById(Integer propertyId) {
        checkArgument(propertyId > 0, "propertyId invalid: %s", propertyId);
        final Property property = propertyRepository.findById(propertyId);
        if (property.getProvinces().isEmpty()) {
            property.setProvinces(provinceRepository.findBy(property.getLocation()));
        }
        return property;
    }

    public Set<Property> findByArea(BoundaryUpperLeft boundaryUpperLeft, BoundaryBottomRight boundaryBottomRight) {
        final Set<Property> properties = propertyRepository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        properties.stream().forEach(property -> {
            if (property.getProvinces().isEmpty()) {
                property.setProvinces(provinceRepository.findBy(property.getLocation()));
            }
        });
        return properties;
    }
}

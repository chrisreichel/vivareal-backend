package br.net.reichel.vivareal.service;

import br.net.reichel.vivareal.config.ValidationSettings;
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

    private final PropertyRepository propertyRepository;
    private final ProvinceRepository provinceRepository;
    private final ValidationSettings rules;

    @Autowired
    public PropertyService(PropertyRepository propertyRepo, ProvinceRepository provinceRepo, ValidationSettings rules) {
        LOG.debug("Using repositories: " + propertyRepo + " and " + provinceRepo);
        this.propertyRepository = propertyRepo;
        this.provinceRepository = provinceRepo;
        this.rules = rules;
    }

    public Property create(Property property) {
        checkArgument(isValidInfo(property), "property contains invalid data");
        final Set<Province> provinces = provinceRepository.findBy(property.getLocation());
        property.setProvinces(provinces);
        return propertyRepository.create(property);
    }

    private boolean isValidInfo(Property p) {
        checkArgument(p != null, "property is invalid");
        checkArgument(p.getBaths() >= rules.getMinBaths() && p.getBaths() <= rules.getMaxBaths(), "invalid no. of baths (%s)", p.getBaths());
        checkArgument(p.getBeds() >= rules.getMinBeds() && p.getBeds() <= rules.getMaxBeds(), "invalid no. of beds (%s)", p.getBeds());
        checkArgument(p.getSquareMeters() >= rules.getMinArea() && p.getSquareMeters() <= rules.getMaxArea(), "invalid sq. meters (%s)", p.getBeds());
        return true;
    }

    public Property findById(Integer propertyId) {
        final Property property = propertyRepository.findById(propertyId);
        if (property != null && property.getProvinces().isEmpty()) {
            property.setProvinces(provinceRepository.findBy(property.getLocation()));
        }
        return property;
    }

    /**
     * Dada uma área compreendida entre os limites superior esquerdo e inferior direito, procura as Propriedades na
     * região.
     *
     * @param boundaryUpperLeft
     * @param boundaryBottomRight
     * @return
     */
    public Set<Property> findByArea(BoundaryUpperLeft boundaryUpperLeft, BoundaryBottomRight boundaryBottomRight) {
        final Set<Property> properties = propertyRepository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        properties.forEach(property -> {
            if (property.getProvinces().isEmpty()) {
                property.setProvinces(provinceRepository.findBy(property.getLocation()));
            }
        });
        return properties;
    }
}

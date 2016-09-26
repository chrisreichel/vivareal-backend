package br.net.reichel.vivareal.service;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.estate.PropertyRepository;
import br.net.reichel.vivareal.domain.location.Province;
import br.net.reichel.vivareal.domain.location.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Service
public class PropertyService {

    private PropertyRepository propertyRepository;
    private ProvinceRepository provinceRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository, ProvinceRepository provinceRepository) {
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

    public Property findById(Long propertyId) {
        return null;
    }

    public Set<Property> queryByCriteria() {
        return Collections.emptySet();
    }
}

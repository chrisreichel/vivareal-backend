package br.net.reichel.vivareal.service;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.estate.PropertyRepository;
import br.net.reichel.vivareal.domain.estate.PropertyRepositoryDefault;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import br.net.reichel.vivareal.domain.location.Province;
import br.net.reichel.vivareal.domain.location.ProvinceRepositoryDefault;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class PropertyServiceTest {

    private PropertyService service;
    private ProvinceRepositoryDefault provinceRepository;
    private PropertyRepository propertyRepository;


    @Before
    public void setUp() throws Exception {
        final Set<Province> mockedData = new HashSet<>();
        mockedData.add(new Province("Scavy", new BoundaryUpperLeft(0, 500), new BoundaryBottomRight(600, 0)));
        mockedData.add(new Province("Groola", new BoundaryUpperLeft(600, 500), new BoundaryBottomRight(800, 0)));

        provinceRepository = new ProvinceRepositoryDefault(mockedData);
        propertyRepository = mock(PropertyRepositoryDefault.class);

        service = new PropertyService(propertyRepository, provinceRepository);
    }

    @Test
    public void shouldCreateAPropertyWithCompleteInfo() throws Exception {
        //Given
        final Property refProperty = buildProperty(1);
        given(propertyRepository.create(any())).willReturn(refProperty);
        //When
        final Property createdProperty = service.create(refProperty);
        //Then
        assertNotNull(createdProperty);
        assertTrue(1 == createdProperty.getProvinces().size());
        assertEquals("Scavy", createdProperty.getProvinces().iterator().next().getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForNumberOfBeds() throws Exception {
        //Given
        final Property refProperty = buildProperty(2);
        given(propertyRepository.create(any())).willReturn(refProperty);
        refProperty.setBeds(33);
        //When
        final Property createdProperty = service.create(refProperty);
        //Then
        assertNotNull(createdProperty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForNumberOfBaths() throws Exception {
        //Given
        final Property refProperty = buildProperty(3);
        given(propertyRepository.create(any())).willReturn(refProperty);
        refProperty.setBaths(0);
        //When
        final Property createdProperty = service.create(refProperty);
        //Then
        assertNotNull(createdProperty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailForSquareMeters() throws Exception {
        //Given
        final Property refProperty = buildProperty(4);
        given(propertyRepository.create(any())).willReturn(refProperty);
        refProperty.setSquareMeters(2000);
        //When
        final Property createdProperty = service.create(refProperty);
        //Then
        assertNotNull(createdProperty);
    }

    @Test
    public void shouldFindByIdAndComplementWithProvince() throws Exception {
        //Given
        final Property refProperty = buildProperty(6);
        given(propertyRepository.findById(6)).willReturn(refProperty);
        //When
        final Property found = service.findById(6);
        //Then
        assertTrue(1 == found.getProvinces().size());
        assertEquals("Scavy", found.getProvinces().iterator().next().getName());
    }

    @Test
    public void shouldFindByAreaAndComplementWithProvince() throws Exception {
        //Given
        final Set<Property> mockedResponse = new HashSet<>();
        mockedResponse.add(buildProperty(8));
        given(propertyRepository.findByArea(any(), any())).willReturn(mockedResponse);
        final BoundaryBottomRight boundaryBottomRight = new BoundaryBottomRight(1, 20);
        final BoundaryUpperLeft boundaryUpperLeft = new BoundaryUpperLeft(20, 10);
        //When
        final Set<Property> found = service.findByArea(boundaryUpperLeft, boundaryBottomRight);
        //Then
        assertTrue(1 == found.size());
        final Property propFound = found.iterator().next();
        assertTrue(1 == propFound.getProvinces().size());
        assertEquals("Scavy", propFound.getProvinces().iterator().next().getName());
    }

    //------------------------------------------------------------------------------------------------------------------

    Property buildProperty(Integer id) {
        final Property p = new Property();
        p.setId(id);
        p.setLocation(new Coordinate(1, 2));
        p.setSquareMeters(100);
        p.setBaths(2);
        p.setBeds(3);
        p.setDescription(id + " Extending to 390 sq. m / 4,200 sq. ft. (approx.) This property is laid out over five floors and provides great potential having retained many of its original period features. The property has a large back garden of 115 ft. (approx.) in length providing excellent potential to extend and create a prime residential or mixed use investment, superb boutique hotel or restoration back into a magnificent residence (subject to P.P) in one of Dublin's premier locations. ");
        p.setPrice(65000 + id);
        p.setTitle("#" + id + " at 89 Lower Baggot Street, Dublin 2");
        return p;
    }
}
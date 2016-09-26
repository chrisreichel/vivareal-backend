package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.config.RepositorySettings;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by ChristianReichel on 9/26/2016.
 */
public class PropertyRepositoryDefaultTest {

    private RepositorySettings repositorySettings;

    @Before
    public void setUp() {
        repositorySettings = new RepositorySettings();
        repositorySettings.setPropertyInputFile("/sample_properties.json");
    }

    @Test
    public void shouldLoadData() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault(repositorySettings);
        //When
        repository.loadData();
        //Then
        assertFalse("no property loaded", repository.getPersistence().isEmpty());
        assertTrue(3 == repository.getPersistence().size());
    }

    @Test
    public void shouldQueryAndFindOneProp() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault(repositorySettings);
        repository.create(buildProperty(1, new Coordinate(1, 1)));
        repository.create(buildProperty(2, new Coordinate(3, 3)));
        repository.create(buildProperty(3, new Coordinate(5, 5)));
        repository.create(buildProperty(4, new Coordinate(7, 7)));
        repository.create(buildProperty(5, new Coordinate(9, 9)));
        final BoundaryUpperLeft boundaryUpperLeft = new BoundaryUpperLeft(6, 8);
        final BoundaryBottomRight boundaryBottomRight = new BoundaryBottomRight(8, 6);
        //When
        final Set<Property> foundProperties = repository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        //Then
        assertTrue(1 == foundProperties.size());
        final Property found = foundProperties.iterator().next();
        assertTrue(4 == found.getId());
        assertTrue(7 == found.getLocation().getLatitude());
        assertTrue(7 == found.getLocation().getLongitude());
    }

    @Test
    public void shouldQueryAndFindTwoProps() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault(repositorySettings);
        repository.create(buildProperty(1, new Coordinate(1, 1)));
        repository.create(buildProperty(2, new Coordinate(3, 3)));
        repository.create(buildProperty(3, new Coordinate(5, 5)));
        repository.create(buildProperty(4, new Coordinate(7, 7)));
        repository.create(buildProperty(5, new Coordinate(9, 9)));
        final BoundaryUpperLeft boundaryUpperLeft = new BoundaryUpperLeft(7, 9);
        final BoundaryBottomRight boundaryBottomRight = new BoundaryBottomRight(9, 7);
        //When
        final Set<Property> foundProperties = repository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        //Then
        assertTrue(2 == foundProperties.size());
        assertThat(foundProperties, hasItem(hasProperty("id", is(4))));
        assertThat(foundProperties, hasItem(hasProperty("id", is(5))));
    }

    @Test
    public void shouldQueryAndFindNothing() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault(repositorySettings);
        repository.create(buildProperty(1, new Coordinate(1, 1)));
        repository.create(buildProperty(2, new Coordinate(3, 3)));
        repository.create(buildProperty(3, new Coordinate(5, 5)));
        repository.create(buildProperty(4, new Coordinate(7, 7)));
        repository.create(buildProperty(5, new Coordinate(17, 17)));
        final BoundaryUpperLeft boundaryUpperLeft = new BoundaryUpperLeft(18, 32);
        final BoundaryBottomRight boundaryBottomRight = new BoundaryBottomRight(64, 18);
        //When
        final Set<Property> foundProperties = repository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        //Then
        assertTrue(foundProperties.isEmpty());
    }

    @Test
    public void shouldQueryAndFindAll() throws Exception {
        //Given
        final PropertyRepositoryDefault repository = new PropertyRepositoryDefault(repositorySettings);
        repository.create(buildProperty(1, new Coordinate(1, 1)));
        repository.create(buildProperty(2, new Coordinate(3, 3)));
        repository.create(buildProperty(3, new Coordinate(5, 5)));
        repository.create(buildProperty(4, new Coordinate(7, 7)));
        repository.create(buildProperty(5, new Coordinate(9, 9)));
        final BoundaryUpperLeft boundaryUpperLeft = new BoundaryUpperLeft(0, 9);
        final BoundaryBottomRight boundaryBottomRight = new BoundaryBottomRight(9, 0);
        //When
        final Set<Property> foundProperties = repository.findByArea(boundaryUpperLeft, boundaryBottomRight);
        //Then
        assertTrue(5 == foundProperties.size());
    }

    //------------------------------------------------------------------------------------------------------------------

    Property buildProperty(Integer id, Coordinate location) {
        final Property property = new Property();
        property.setId(id);
        property.setBaths(2);
        property.setBeds(2);
        property.setDescription("Teste JUNIT");
        property.setTitle("JUNIT");
        property.setSquareMeters(100);
        property.setPrice(123456);
        property.setLocation(location);
        return property;
    }

}
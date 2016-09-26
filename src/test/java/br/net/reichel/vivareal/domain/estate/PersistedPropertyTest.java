package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.geographic.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ChristianReichel on 9/26/2016.
 */
public class PersistedPropertyTest {

    @Test
    public void shouldGenerateAProperty() throws Exception {
        //Given
        final PersistedProperty persistedProp = new PersistedProperty();
        persistedProp.setId(69);
        persistedProp.setLatitude(222);
        persistedProp.setLongitude(444);
        persistedProp.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        persistedProp.setPrice(1250000);
        persistedProp.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        persistedProp.setBeds(4);
        persistedProp.setBaths(3);
        persistedProp.setSquareMeters(210);
        persistedProp.setLocation(new Coordinate(456, 123));
        //When
        final Property property = persistedProp.toProperty();
        //Then
        assertTrue(444 == property.getLocation().getLongitude());
        assertTrue(222 == property.getLocation().getLatitude());
        assertEquals("Imóvel código 1, com 5 quartos e 4 banheiros", persistedProp.getTitle());
        assertNull(property.getId());
        assertTrue(property.getProvinces().isEmpty());
    }

}
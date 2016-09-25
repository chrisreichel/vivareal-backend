package br.net.reichel.vivareal.web.event;

import br.net.reichel.vivareal.domain.estate.Property;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import br.net.reichel.vivareal.domain.location.Province;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class PropertyRequestTest {

    @Test
    public void shouldTranslateToPropertyDomain() throws Exception {
        //Given
        final PropertyRequest request = new PropertyRequest();
        request.setId(51L);
        request.setX("222");
        request.setY("444");
        request.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        request.setPrice(1250000);
        request.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        request.setBeds(4);
        request.setBaths(3);
        request.setSquareMeters(210);
        request.addProvince(new Province("Taubaté", new BoundaryUpperLeft(1, 2), new BoundaryBottomRight(3, 4)));
        request.setLocation(new Coordinate(123, 456));
        //When
        final Property property = request.toProperty();
        //Then
        assertTrue(222 == property.getLocation().getLongitude());
        assertTrue(444 == property.getLocation().getLatitude());
        assertEquals("Imóvel código 1, com 5 quartos e 4 banheiros", request.getTitle());
        assertNull(property.getId());
        assertTrue(property.getProvinces().isEmpty());
    }

}
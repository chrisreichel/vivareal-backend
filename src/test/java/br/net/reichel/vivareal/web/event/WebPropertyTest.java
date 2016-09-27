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
public class WebPropertyTest {

    @Test
    public void shouldTranslateToPropertyDomain() throws Exception {
        //Given
        final WebProperty request = new WebProperty();
        request.setId(51);
        request.setX(444);
        request.setY(222);
        request.setTitle("Imóvel código 1, com 5 quartos e 4 banheiros");
        request.setPrice(1250000);
        request.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        request.setBeds(4);
        request.setBaths(3);
        request.setSquareMeters(210);
        request.addProvince(new Province("Taubaté", new BoundaryUpperLeft(1, 2), new BoundaryBottomRight(3, 4)));
        request.setLocation(new Coordinate(456, 123));
        //When
        final Property property = request.toProperty();
        //Then
        assertTrue(222 == property.getLocation().getLongitude());
        assertTrue(444 == property.getLocation().getLatitude());
        assertEquals("Imóvel código 1, com 5 quartos e 4 banheiros", request.getTitle());
        assertNull(property.getId());
        assertTrue(property.getProvinces().isEmpty());
    }

    @Test
    public void shouldBuildAWebPropertyFromProperty() throws Exception {
        //Given
        final Property p = new Property();
        p.setId(11);
        p.setLocation(new Coordinate(1, 2));
        p.setSquareMeters(100);
        p.setBaths(2);
        p.setBeds(3);
        p.setDescription("Magical place @ Dublin");
        p.setPrice(35000);
        p.setTitle("89 Lower Baggot Street, Dublin 2");
        p.addProvince(new Province("Testing", new BoundaryUpperLeft(1, 2), new BoundaryBottomRight(2, 1)));
        //When
        final WebProperty actual = new WebProperty(p);
        //Then
        assertTrue(p.getId() == actual.getId());
        assertTrue(p.getProvinces().size() == actual.getProvinces().size());
    }
}
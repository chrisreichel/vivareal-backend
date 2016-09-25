package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.location.Province;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class PropertyTest {

    @Test
    public void shouldNotGetNullProvinces() throws Exception {
        //Given
        final Property property = new Property();
        //When and Then
        assertNotNull(property.getProvinces());
        assertEquals(0, property.getProvinces().size());
    }

    @Test
    public void shouldGetASingleProvinces() throws Exception {
        //Given
        final Property property = new Property();
        //When
        property.addProvince(new Province("teste", new BoundaryUpperLeft(1, 2), new BoundaryBottomRight(3, 4)));
        //Then
        assertEquals(1, property.getProvinces().size());
    }
}
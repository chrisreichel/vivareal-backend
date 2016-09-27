package br.net.reichel.vivareal.web;

import br.net.reichel.vivareal.web.event.EnclosedWebProperties;
import br.net.reichel.vivareal.web.event.WebProperty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Integration tests
 * Created by Christian Reichel on 9/26/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyControllerTest {

    @LocalServerPort
    private int port;
    private URL baseUrl;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.baseUrl = new URL("http://localhost:" + port + "/properties");
    }

    @Test
    public void shouldPerformGetById() throws Exception {
        //Given
        final String fullUrl = baseUrl.toString() + "/1";
        //When
        final ResponseEntity<WebProperty> response = template.getForEntity(fullUrl, WebProperty.class);
        final WebProperty property = response.getBody();
        //Then
        assertTrue(1257 == property.getX());
        assertTrue(928 == property.getY());
        assertTrue(1 == property.getId());
        assertTrue(3 == property.getBeds());
        assertTrue(2 == property.getBaths());
        assertEquals("Imóvel código 1, com 3 quartos e 2 banheiros.", property.getTitle());
        assertTrue(1 == property.getProvinces().size());
        assertEquals("Jaby", property.getProvinces().iterator().next().getName());
    }

    @Test
    public void shouldCreatPropertyAndAddAnId() throws Exception {
        //Given
        final WebProperty request = new WebProperty();
        request.setTitle("Imóvel código 666, com 5 quartos e 4 banheiros");
        request.setPrice(1250000);
        request.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        request.setBeds(5);
        request.setBaths(4);
        request.setSquareMeters(210);
        request.setX(10);
        request.setY(20);
        //When
        final ResponseEntity<WebProperty> response = template.postForEntity(baseUrl.toString(), request, WebProperty.class);
        //Then
        final WebProperty created = response.getBody();
        //No
        assertTrue(10 == created.getX());
        assertTrue(20 == created.getY());
        assertTrue(4 == created.getId());
        assertTrue(5 == created.getBeds());
        assertTrue(4 == created.getBaths());
        assertEquals("Imóvel código 666, com 5 quartos e 4 banheiros", created.getTitle());
        assertTrue(1 == created.getProvinces().size());
        assertEquals("Scavy", created.getProvinces().iterator().next().getName());
    }

    @Test
    public void shouldSearchByArea() throws Exception {
        //Given
        final String fullUrl = baseUrl.toString() + "/?ax=1200&ay=1000&bx=1400&by=800";
        //When
        final ResponseEntity<EnclosedWebProperties> response = template.getForEntity(fullUrl, EnclosedWebProperties.class);
        //Then
        final EnclosedWebProperties found = response.getBody();
        assertTrue(1 == found.getTotalProperties());
        final WebProperty property = found.getProperties().iterator().next();
        assertTrue(1 == property.getId());
    }
}
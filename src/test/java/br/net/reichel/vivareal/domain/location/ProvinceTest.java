package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertTrue;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class ProvinceTest {

    private Province referenceProvince;

    @Before
    public void setUp() {
        //(x, y) -> (latitude, longitude)
        final BoundaryUpperLeft upperLeft = new BoundaryUpperLeft(0, 500);
        final BoundaryBottomRight bottomRight = new BoundaryBottomRight(600, 0);
        referenceProvince = new Province("JUNIT", upperLeft, bottomRight);
    }

    @Test
    public void shouldGetBoundaryBottomLeft() throws Exception {
        //Given and When @ setUp
        //Then
        assertThat(0, is(equalTo(referenceProvince.getBoundaryBottomLeft().getLongitude())));
        assertThat(0, is(equalTo(referenceProvince.getBoundaryBottomLeft().getLatitude())));
    }

    @Test
    public void shouldGetBoundaryUpperRight() throws Exception {
        //Given and When @ setUp
        //Then
        assertThat(500, is(equalTo(referenceProvince.getBoundaryUpperRight().getLongitude())));
        assertThat(600, is(equalTo(referenceProvince.getBoundaryUpperRight().getLatitude())));
    }

    @Test
    public void shouldBeInsideBoundaries() throws Exception {
        //Given @ setUp
        //When
        final Coordinate location = new Coordinate(10, 20);
        //Then
        assertTrue(referenceProvince.contains(location));
    }

    @Test
    public void shouldBeInsideBoundariesAtEndEdge() throws Exception {
        //Given @ setUp
        //When
        final Coordinate location = new Coordinate(600, 500);
        //Then
        assertTrue(referenceProvince.contains(location));
    }

    @Test
    public void shouldBeInsideBoundariesAtBeginningEdge() throws Exception {
        //Given @ setUp
        //When
        final Coordinate location = new Coordinate(0, 0);
        //Then
        assertTrue(referenceProvince.contains(location));
    }

    @Test
    public void shouldBeBetweenBoundLongitudes() {
        //Given @ setUp
        //When
        final Coordinate location = new Coordinate(10, 20);
        //Then
        assertTrue(referenceProvince.isBetweenBoundLongitudes(location.getLongitude()));
    }

    @Test
    public void shouldBeBetweenBoundLatitudes() {
        //Given @ setUp
        //When
        final Coordinate location = new Coordinate(10, 20);
        //Then
        assertTrue(referenceProvince.isBetweenBoundLatitudes(location.getLatitude()));
    }

}
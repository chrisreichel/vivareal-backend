package br.net.reichel.vivareal.domain;

import org.junit.Test;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class CoordinateTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotNull() throws Exception {
        new Coordinate(1, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeNegative() throws Exception {
        new Coordinate(-1, 1);
    }
}
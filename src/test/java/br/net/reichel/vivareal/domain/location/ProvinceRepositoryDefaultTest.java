package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.Coordinate;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class ProvinceRepositoryDefaultTest {

    private ProvinceRepositoryDefault provinceRepository;

    @Before
    public void setUp() throws Exception {
        provinceRepository = new ProvinceRepositoryDefault();
        provinceRepository.loadData();
    }

    @Test
    public void shouldGetAll() throws Exception {
        //Given setUp()
        //When
        final Set<Province> allProvinces = provinceRepository.getAll();
        //Then
        assertTrue("deveriam ser 6, mas encontrou: " + allProvinces.size(), 6 == allProvinces.size());
    }

    @Test
    public void shouldFindSingleProvinceAtNova() throws Exception {
        //Given setUp()
        final Integer longitude = 100;
        final Integer latitude = 1300;
        //When
        final Set<Province> found = provinceRepository.findBy(new Coordinate(latitude, longitude));
        //Then
        assertTrue("deveria ser 1, mas encontrou: " + found.size(), 1 == found.size());
        final Province province = found.iterator().next();
        assertEquals("Nova", province.getName());
    }

    @Test
    public void shouldFindTwoProvinces() throws Exception {
        //Given setUp()
        final Integer longitude = 500;
        final Integer latitude = 700;
        //When
        final Set<Province> found = provinceRepository.findBy(new Coordinate(latitude, longitude));
        //Then
        assertTrue("deveriam ser 2, mas encontrou: " + found.size(), 2 == found.size());
    }

    /**
     * Consideranto que multiplas áreas se encontro no mesmo ponto (multipla fronteira)
     *
     * @throws Exception
     */
    @Test
    public void shouldFind4Provinces() throws Exception {
        //Given setUp()
        final Integer longitude = 500;
        final Integer latitude = 600;
        //When
        final Set<Province> found = provinceRepository.findBy(new Coordinate(latitude, longitude));
        //Then
        assertTrue("deveriam ser 4, mas encontrou: " + found.size(), 4 == found.size());
    }
}
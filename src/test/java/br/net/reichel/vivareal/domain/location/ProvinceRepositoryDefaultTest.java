package br.net.reichel.vivareal.domain.location;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public class ProvinceRepositoryDefaultTest {

    @Test
    public void shouldGetAll() throws Exception {
        //Given
        final ProvinceRepositoryDefault provinceRepository = new ProvinceRepositoryDefault();
        provinceRepository.loadData();
        //When
        final Set<Province> allProvinces = provinceRepository.getAll();
        //Then
        assertTrue(6 == allProvinces.size());
        System.out.printf("Provinces: " + allProvinces.toString());
    }

}
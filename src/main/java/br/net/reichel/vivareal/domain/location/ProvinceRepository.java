package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.Coordinate;

import java.util.Set;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public interface ProvinceRepository {

    Set<Province> getAll();

    Set<Province> findBy(Coordinate coordinate);

}

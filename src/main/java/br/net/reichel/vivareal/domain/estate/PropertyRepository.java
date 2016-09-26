package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;

import java.util.Set;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
public interface PropertyRepository {

    Property create(Property property);

    Set<Property> findByArea(BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight);

}

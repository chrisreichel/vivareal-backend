package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.location.ProvinceRepositoryDefault;
import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.index.quadtree.Quadtree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Repository
public class PropertyRepositoryDefault implements PropertyRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProvinceRepositoryDefault.class);

    private final Map<Long, Property> persistence = new HashMap<>();

    @PostConstruct
    public void loadData() throws Exception {
        //http://tsusiatsoftware.net/jts/javadoc/com/vividsolutions/jts/index/quadtree/Quadtree.html
        final Quadtree quadtree = new Quadtree();
        Envelope envelope = new Envelope();
        quadtree.insert(envelope, null);
    }

    @Override
    public Property create(Property property) {
        return null;
    }
}

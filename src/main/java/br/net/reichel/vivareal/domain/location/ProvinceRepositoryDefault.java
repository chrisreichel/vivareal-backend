package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Repository
public class ProvinceRepositoryDefault implements ProvinceRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProvinceRepositoryDefault.class);

    private Set<Province> db = new HashSet<>();

    public ProvinceRepositoryDefault() {
    }

    public ProvinceRepositoryDefault(Set<Province> mockedData) {
        db = mockedData;
    }

    @PostConstruct
    public void loadData() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode rootNode = mapper.readTree(this.getClass().getResourceAsStream("/provinces.json"));
        rootNode.fieldNames().forEachRemaining(nodeName -> {
            final String name = nodeName;
            try {
                final JsonNode upperLeftNode = rootNode.get(name).get("boundaries").get("upperLeft");
                final JsonNode bottomRightNode = rootNode.get(name).get("boundaries").get("bottomRight");
                final Coordinate upperLeft = mapper.treeToValue(upperLeftNode, Coordinate.class);
                final Coordinate bottomRight = mapper.treeToValue(bottomRightNode, Coordinate.class);
                db.add(new Province(name, new BoundaryUpperLeft(upperLeft), new BoundaryBottomRight(bottomRight)));
            } catch (Throwable e) {
                LOG.error("error at province: " + name + " - " + e.getMessage());
            }
        });
    }

    @Override
    public Set<Province> getAll() {
        return Collections.unmodifiableSet(db);
    }

    @Override
    public Set<Province> findBy(final Coordinate coordinate) {
        return db.stream().filter(province -> province.contains(coordinate)).collect(toSet());
    }


}

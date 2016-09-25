package br.net.reichel.vivareal.domain.location;

import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Repository
public class ProvinceRepositoryDefault implements ProvinceRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProvinceRepositoryDefault.class);

    private Set<Province> persistence = new HashSet<>();

    @PostConstruct
    public void loadData() throws Exception {
        final byte[] jsonFile = Files.readAllBytes(Paths.get("C:\\Users\\HE-SERIES\\Documents\\vivareal-backend\\src\\main\\resources\\provinces.json"));
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode rootNode = mapper.readTree(jsonFile);
        rootNode.fieldNames().forEachRemaining(nodeName -> {
            final String name = nodeName;
            final JsonNode upperLeftNode = rootNode.get(name).get("boundaries").get("upperLeft");
            final JsonNode bottomRightNode = rootNode.get(name).get("boundaries").get("bottomRight");
            System.out.println("nodeName: " + name);
            System.out.println("node content: " + rootNode.get(name).get("boundaries"));
            System.out.println("upperLeftNode: " + upperLeftNode);
            System.out.println("bottomRightNode: " + bottomRightNode);
            try {
                final Coordinate upperLeft = mapper.treeToValue(upperLeftNode, Coordinate.class);
                final Coordinate bottomRight = mapper.treeToValue(bottomRightNode, Coordinate.class);
                persistence.add(new Province(name, new BoundaryUpperLeft(upperLeft), new BoundaryBottomRight(bottomRight)));
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage());
            }
            System.out.println("-----");
        });
        System.out.println(rootNode);
        persistence = mapper.readValue(jsonFile, new TypeReference<Coordinate>() {
        });
    }

    @Override
    public Set<Province> getAll() {
        return persistence;
    }


}

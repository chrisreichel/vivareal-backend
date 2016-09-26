package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.config.RepositorySettings;
import br.net.reichel.vivareal.domain.geographic.BoundaryBottomRight;
import br.net.reichel.vivareal.domain.geographic.BoundaryUpperLeft;
import br.net.reichel.vivareal.domain.geographic.Coordinate;
import br.net.reichel.vivareal.domain.location.ProvinceRepositoryDefault;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infomatiq.jsi.Rectangle;
import com.infomatiq.jsi.SpatialIndex;
import com.infomatiq.jsi.rtree.RTree;
import gnu.trove.TIntProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

import static java.util.stream.Collectors.toSet;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Repository
public class PropertyRepositoryDefault implements PropertyRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProvinceRepositoryDefault.class);

    private final Map<Integer, Property> persistence = new HashMap<>();
    private final SpatialIndex spatialIndex = new RTree();

    private RepositorySettings repositorySettings;

    @Autowired
    public PropertyRepositoryDefault(RepositorySettings repositorySettings) {
        this.repositorySettings = repositorySettings;
        spatialIndex.init(null);
    }

    @PostConstruct
    public void loadData() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode rootNode = mapper.readTree(this.getClass().getResourceAsStream(repositorySettings.getPropertyInputFile()));
        rootNode.get("properties").elements().forEachRemaining(propNode -> {
            PersistedProperty persisted = null;
            try {
                persisted = mapper.treeToValue(propNode, PersistedProperty.class);
                LOG.debug("loading data " + persisted);
                insert(persisted.toProperty());
            } catch (JsonProcessingException e) {
                LOG.error("error loading properties data from ths json", e);
            } catch (Exception e) {
                LOG.error("error generating the Property domain for PersistedProperty:" + persisted, e);
            }
        });
    }

    @Override
    public synchronized Property create(Property refProp) {
        return insert(refProp);
    }

    private Property insert(Property refProp) {
        final Property property = new Property();
        BeanUtils.copyProperties(refProp, property);
        property.setId(persistence.size() + 1);
        persistence.put(property.getId(), property);
        spatialIndex.add(getRectangleFrom(property), property.getId());
        return property;
    }

    /**
     * A busca utiliza um R-Tree based Spatial Index". É basicamente uma árvore balenceada.
     * Estima-se que o Big O é:
     * * Busca: O(log n) - (eu imagino que deva haver uma pequena penalização em relação a b-tree)
     * * Inserção: O(n) (pior caso)
     *
     * @param upperLeft
     * @param bottomRight
     * @return
     */
    @Override
    public Set<Property> findByArea(BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight) {
        final Set<Property> propertiesFound = new HashSet<>();
        final SaveToCollectionProcedure queryProcedure = new SaveToCollectionProcedure();
        spatialIndex.contains(getRectangleFrom(upperLeft, bottomRight), queryProcedure);
        queryProcedure.getIds().stream().map(id -> propertiesFound.add(persistence.get(id))).collect(toSet());
        return propertiesFound;
    }

    @Override
    public Property findById(Integer propertyId) {
        return persistence.get(propertyId);
    }

    //------------------------------------------------------------------------------------------------------------------

    //For testing
    Map<Integer, Property> getPersistence() {
        return Collections.unmodifiableMap(persistence);
    }

    private Rectangle getRectangleFrom(Property property) {
        final Coordinate local = property.getLocation();
        return new Rectangle(local.getLatitude(), local.getLongitude(), local.getLatitude(), local.getLongitude());
    }

    private Rectangle getRectangleFrom(BoundaryUpperLeft upperLeft, BoundaryBottomRight bottomRight) {
        return new Rectangle(upperLeft.getLatitude(), upperLeft.getLongitude(), bottomRight.getLatitude(), bottomRight.getLongitude());
    }

    class SaveToCollectionProcedure implements TIntProcedure {
        private Collection<Integer> ids = new LinkedList<>();

        public boolean execute(int id) {
            ids.add(id);
            return true;
        }
        private Collection<Integer> getIds() {
            return ids;
        }
    }
}

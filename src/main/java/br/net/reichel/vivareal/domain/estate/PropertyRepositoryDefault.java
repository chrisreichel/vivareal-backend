package br.net.reichel.vivareal.domain.estate;

import br.net.reichel.vivareal.domain.location.ProvinceRepositoryDefault;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.net.URL;
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
        URL url = this.getClass().getResource("properties.json");
        final ObjectMapper mapper = new ObjectMapper();
    }

    @Override
    public Property create(Property property) {
        return null;
    }
}

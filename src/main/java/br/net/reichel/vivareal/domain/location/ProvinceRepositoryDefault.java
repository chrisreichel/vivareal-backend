package br.net.reichel.vivareal.domain.location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Christian Reichel on 9/25/2016.
 */
@Repository
public class ProvinceRepositoryDefault implements ProvinceRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ProvinceRepositoryDefault.class);

    private final Set<Province> inMemoryPersistence = new HashSet<>();

    @PostConstruct
    public void loadData() throws Exception {

    }

    @Override
    public Set<Province> getAll() {
        return null;
    }


}

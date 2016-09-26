package br.net.reichel.vivareal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ChristianReichel on 9/26/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "data")
public class RepositorySettings {

    private String propertyInputFile;
    private String provinceInputFile;

    public String getPropertyInputFile() {
        return propertyInputFile;
    }

    public void setPropertyInputFile(String propertyInputFile) {
        this.propertyInputFile = propertyInputFile;
    }

    public String getProvinceInputFile() {
        return provinceInputFile;
    }

    public void setProvinceInputFile(String provinceInputFile) {
        this.provinceInputFile = provinceInputFile;
    }
}

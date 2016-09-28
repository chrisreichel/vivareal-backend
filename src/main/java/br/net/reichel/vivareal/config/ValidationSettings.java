package br.net.reichel.vivareal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ChristianReichel on 9/28/2016.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "validation")
public class ValidationSettings {

    private Integer maxBeds, minBeds, maxBaths, minBaths, maxArea, minArea;

    public Integer getMaxBeds() {
        return maxBeds;
    }

    public void setMaxBeds(Integer maxBeds) {
        this.maxBeds = maxBeds;
    }

    public Integer getMinBeds() {
        return minBeds;
    }

    public void setMinBeds(Integer minBeds) {
        this.minBeds = minBeds;
    }

    public Integer getMaxBaths() {
        return maxBaths;
    }

    public void setMaxBaths(Integer maxBaths) {
        this.maxBaths = maxBaths;
    }

    public Integer getMinBaths() {
        return minBaths;
    }

    public void setMinBaths(Integer minBaths) {
        this.minBaths = minBaths;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    @Override
    public String toString() {
        return "ValidationSettings{" +
                "maxBeds=" + maxBeds +
                ", maxBeds=" + maxBeds +
                ", minBeds=" + minBeds +
                ", maxBaths=" + maxBaths +
                ", minBaths=" + minBaths +
                ", maxArea=" + maxArea +
                ", minArea=" + minArea +
                '}';
    }
}

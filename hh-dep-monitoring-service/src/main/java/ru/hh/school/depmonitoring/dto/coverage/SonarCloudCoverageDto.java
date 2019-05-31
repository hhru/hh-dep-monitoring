package ru.hh.school.depmonitoring.dto.coverage;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonAutoDetect
@JsonRootName(value = "component")
public class SonarCloudCoverageDto implements Serializable {
    private String id;
    private String key;
    private String name;
    private String qualifier;
    private Measure[] measures;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getQualifier() {
        return qualifier;
    }

    public Measure[] getMeasures() {
        return measures;
    }

    public static class Measure implements Serializable {
        private String metric;
        private float value;
        private boolean bestValue;

        public String getMetric() {
            return metric;
        }

        public float getValue() {
            return value;
        }

        public boolean isBestValue() {
            return bestValue;
        }
    }
}

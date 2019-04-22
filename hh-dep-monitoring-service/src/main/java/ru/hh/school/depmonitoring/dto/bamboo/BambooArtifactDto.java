package ru.hh.school.depmonitoring.dto.bamboo;

import java.util.List;

public class BambooArtifactDto {
    private String key;
    private String name;
    private String type;
    private String orgName;
    private String repoName;
    private String viewPath;
    private String lastCommitDateTime;
    private List<BambooArtifactDto> dependencyTree;

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getViewPath() {
        return viewPath;
    }

    public String getLastCommitDateTime() {
        return lastCommitDateTime;
    }

    public List<BambooArtifactDto> getDependencyTree() {
        return dependencyTree;
    }
}

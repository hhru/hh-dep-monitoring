package ru.hh.school.depmonitoring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "coverage")
public class Coverage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coverage_coverage_id_seq")
    @SequenceGenerator(name = "coverage_coverage_id_seq", allocationSize = 1, sequenceName = "coverage_coverage_id_seq")
    @Column(name = "coverage_id")
    private Integer coverageId;

    @NotNull
    @Column(name = "analize_time")
    private LocalDateTime analizeTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "source_type")
    @NotNull
    private CoverageSourceType sourceType;

    @Column(name = "repository_id")
    private Long repositoryId;

    @Column(name = "coverage")
    private Float coverage;

    public Coverage(LocalDateTime analizeTime, CoverageSourceType sourceType, Long repositoryId, Float coverage) {
        this.coverageId = coverageId;
        this.analizeTime = analizeTime;
        this.sourceType = sourceType;
        this.repositoryId = repositoryId;
        this.coverage = coverage;
    }

    private Coverage() {
    }

    public Integer getCoverageId() {
        return coverageId;
    }

    public void setCoverageId(Integer coverageId) {
        this.coverageId = coverageId;
    }

    public LocalDateTime getBuildTime() {
        return analizeTime;
    }

    public void setBuildTime(LocalDateTime buildTime) {
        this.analizeTime = buildTime;
    }

    public CoverageSourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(CoverageSourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Float getCoverage() {
        return coverage;
    }

    public void setCoverage(Float coverage) {
        this.coverage = coverage;
    }
}

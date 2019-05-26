package ru.hh.school.depmonitoring.service.loaders;

import ru.hh.school.depmonitoring.exceptions.LoadException;

import java.io.IOException;

public interface CoverageSource {
    float getCoverage(String key) throws LoadException, IOException;

}

package ru.hh.school.depmonitoring.utils;

import org.kohsuke.github.GHRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.hh.school.depmonitoring.dto.github.GHRepositoryDto;
import java.io.IOException;
import java.util.Date;

public class GHConverter {
    private static final Logger log = LoggerFactory.getLogger(GHConverter.class);

    private GHConverter() {
    }

    public static GHRepositoryDto convert(GHRepository ghRepository) {
        return GHRepositoryDto.builder()
                .withRepositoryId(ghRepository.getId())
                .withName(ghRepository.getName())
                .withHtmlUrl(ghRepository.getHtmlUrl())
                .withDescription(ghRepository.getDescription())
                .withArchived(ghRepository.isArchived())
                .withActive(true)
                .withCreatedAt(getCreatedDate(ghRepository))
                .withUpdatedAt(getUpdatedDate(ghRepository))
                .build();
    }

    private static Date getCreatedDate(GHRepository ghRepository) {
        try {
            return ghRepository.getCreatedAt();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Date getUpdatedDate(GHRepository ghRepository) {
        try {
            return ghRepository.getUpdatedAt();
        } catch (IOException e) {
            log.error("Error in getting update date for repository with id = {} and name = {}", ghRepository.getId(), ghRepository.getName(), e);
            return null;
        }
    }
}

package ru.hh.school.depmonitoring.service.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Mapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);

    default List<D> toDto(Collection<E> entities) {
        return Stream.ofNullable(entities)
                .flatMap(Collection::stream)
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    default List<E> toEntity(Collection<D> entities) {
        return Stream.ofNullable(entities)
                .flatMap(Collection::stream)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

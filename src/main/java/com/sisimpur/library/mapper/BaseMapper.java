package com.sisimpur.library.mapper;

public interface BaseMapper <E,D>{
    E toEntity(D dto);
    D toDto(E entity);

    E updateEntityFromDto(D dto, E entity);
}

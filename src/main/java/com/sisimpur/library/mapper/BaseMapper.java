package com.sisimpur.library.mapper;

import org.mapstruct.MappingTarget;

public interface BaseMapper <E,D>{
    E toEntity(D dto);
    D toDto(E entity);

    E updateEntityFromDto(D dto, @MappingTarget E entity);
}

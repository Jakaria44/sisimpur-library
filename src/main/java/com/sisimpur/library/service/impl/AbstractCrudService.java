package com.sisimpur.library.service.impl;

import com.sisimpur.library.exception.ResourceNotFoundException;
import com.sisimpur.library.exception.ServiceException;
import com.sisimpur.library.mapper.BaseMapper;
import com.sisimpur.library.repository.SearchableRepository;
import com.sisimpur.library.service.GenericService;
import com.sisimpur.library.model.Activatable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import jakarta.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractCrudService<E extends Activatable, D, ID> implements GenericService<E, D, ID> {

    protected abstract SearchableRepository<E, ID> getRepository();
    protected abstract BaseMapper<E, D> getMapper();

    @Override
    public D create(D dto) {
        try {
            E entity = getMapper().toEntity(dto);
            E savedEntity = getRepository().save(entity);
            log.info("Created entity: {}", savedEntity);
            return getMapper().toDto(savedEntity);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            log.error("Data integrity issue when creating entity: {}", ex.getMessage(), ex);
            throw new ServiceException("Data integrity error occurred during creation.", ex);
        } catch (Exception ex) {
            log.error("Error creating entity: {}", ex.getMessage(), ex);
            throw new ServiceException("An unexpected error occurred during creation.", ex);
        }
    }
    @Cacheable(value = "entityCache", key = "#id")
    @Override
    public D getById(ID id) {
        try {
            E entity = getRepository().findById(id)
                    .orElseThrow(() -> {
                        log.error("Resource not found with id: {}", id);
                        return new ResourceNotFoundException("Resource not found with id: " + id);
                    });
            return getMapper().toDto(entity);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error retrieving entity with id {}: {}", id, ex.getMessage(), ex);
            throw new ServiceException("An unexpected error occurred while retrieving the entity.", ex);
        }
    }

    @CacheEvict(value = "entityCache", key = "#id")
    @Override
    public D update(ID id, D dto) {
        try {
            E existingEntity = getRepository().findById(id)
                    .orElseThrow(() -> {
                        log.error("Resource not found with id: {}", id);
                        return new ResourceNotFoundException("Resource not found with id: " + id);
                    });
            E updatedEntity = getRepository().save(getMapper().updateEntityFromDto(dto, existingEntity));
            log.info("Updated entity with id: {}", id);
            return getMapper().toDto(updatedEntity);
        } catch (DataIntegrityViolationException | ConstraintViolationException ex) {
            log.error("Data integrity issue when updating entity with id {}: {}", id, ex.getMessage(), ex);
            throw new ServiceException("Data integrity error occurred during update.", ex);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error updating entity with id {}: {}", id, ex.getMessage(), ex);
            throw new ServiceException("An unexpected error occurred during update.", ex);
        }
    }


    @CacheEvict(value = "entityCache", key = "#id")
    @Override
    public void delete(ID id) {
        try {
            E entity = getRepository().findById(id)
                    .orElseThrow(() -> {
                        log.error("Resource not found with id: {}", id);
                        return new ResourceNotFoundException("Resource not found with id: " + id);
                    });
            // Soft delete: mark the entity as inactive.
            entity.setActive(false);
            getRepository().save(entity);
            log.info("Soft deleted entity with id: {}", id);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("Error soft deleting entity with id {}: {}", id, ex.getMessage(), ex);
            throw new ServiceException("An unexpected error occurred during soft deletion.", ex);
        }
    }


    @Override
    public Page<D> getAll(Integer pageSize, Integer pageNumber, String searchBy, String searchVal,
                                  String sortBy, String sortDirection, String isActive) {
        try {
            Sort.Direction direction = "DESC".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC;
            PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(direction, sortBy));

            Boolean isActiveStatus = null;
            if ("true".equalsIgnoreCase(isActive)) {
                isActiveStatus = true;
            } else if ("false".equalsIgnoreCase(isActive)) {
                isActiveStatus = false;
            }

            Page<E> modelPage;
            if (searchBy != null && searchVal != null) {
                modelPage = getRepository().findAllBySearchValAndSearchByAndActive(searchVal, searchBy, isActiveStatus, pageRequest);
            } else if (isActiveStatus != null) {
                modelPage = getRepository().findAllByIsActive(isActiveStatus, pageRequest);
            } else {
                modelPage = getRepository().findAll(pageRequest);
            }

            List<D> dtos = modelPage.getContent().stream()
                    .map(getMapper()::toDto)
                    .collect(Collectors.toList());

            log.info("Advanced search returned {} results", dtos.size());
            return new PageImpl<>(dtos, pageRequest, modelPage.getTotalElements());
        } catch (Exception ex) {
            log.error("Error during advanced search: {}", ex.getMessage(), ex);
            throw new ServiceException("An unexpected error occurred during advanced search.", ex);
        }
    }
}

package com.dylandewit.skeleton.api.base;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.base.dto.BasePostDto;
import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public abstract class BaseService<ENTITY extends BaseModel, GET_DTO extends BaseGetDto<ENTITY>, POST_DTO extends BasePostDto<ENTITY>> {
    protected final BaseRepository<ENTITY> repository;

    protected BaseService(BaseRepository<ENTITY> repository) {
        this.repository = repository;
    }

    public Page<ENTITY> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Set<ENTITY> findAll() {
        return repository.findAll();
    }

    public Page<ENTITY> findByIds(List<Long> ids, Pageable pageable) {
        return repository.findAllByIdIn(ids, pageable);
    }

    public Set<ENTITY> findByIds(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    public ENTITY findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public ENTITY update(Long id, POST_DTO dto) {
        ENTITY t = mapForUpdate(findById(id), dto);

        return repository.save(t);
    }

    public ENTITY create(POST_DTO dto) {
        return repository.save(mapForCreate(dto));
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    protected ENTITY mapForCreate(POST_DTO dto) {
        return dto.toModel();
    }

    protected abstract GET_DTO mapToDto(ENTITY t);

    protected abstract ENTITY mapForUpdate(ENTITY t, POST_DTO dto);
}

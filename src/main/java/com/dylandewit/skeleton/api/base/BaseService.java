package com.dylandewit.skeleton.api.base;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.base.dto.BasePostDto;
import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public abstract class BaseService<T extends BaseModel, VIEW_DTO extends BaseGetDto<T>, CREATE_DTO extends BasePostDto<T>> {
    protected final BaseRepository<T> repository;

    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Set<T> findAll() {
        return repository.findAll();
    }

    public Page<T> findByIds(List<Long> ids, Pageable pageable) {
        return repository.findAllByIdIn(ids, pageable);
    }

    public Set<T> findByIds(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    public T findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public T update(Long id, CREATE_DTO dto) {
        T t = mapForUpdate(findById(id), dto);

        return repository.save(t);
    }

    public T create(CREATE_DTO dto) {
        return repository.save(mapForCreate(dto));
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }

    protected T mapForCreate(CREATE_DTO dto) {
        return dto.toModel();
    }

    protected abstract VIEW_DTO mapToDto(T t);

    protected abstract T mapForUpdate(T t, CREATE_DTO dto);
}

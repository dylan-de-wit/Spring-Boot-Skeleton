package com.dylandewit.skeleton.resources;

import com.dylandewit.skeleton.exception.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public abstract class BaseService<T extends BaseModel, VIEW_DTO extends BaseViewDto<T>, CREATE_DTO extends BaseCreateDto<T>> {
    protected final BaseRepository<T> repository;

    protected BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public Page<VIEW_DTO> findAll(List<String> includes, Pageable pageable) {
        return repository.findAll(pageable).map(t -> mapToDto(t, includes));
    }

    public VIEW_DTO findById(Long id, List<String> includes) {
        return mapToDto(find(id), includes);
    }

    public Page<VIEW_DTO> findByIds(List<Long> ids, List<String> includes, Pageable pageable) {
        return repository.findAllByIdIn(ids, pageable).map(t -> mapToDto(t, includes));
    }

    public Set<T> find(List<Long> ids) {
        return repository.findAllByIdIn(ids);
    }

    public T find(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public VIEW_DTO update(Long id, CREATE_DTO dto, List<String> includes) {
        T t = mapForUpdate(find(id), dto);

        return mapToDto(repository.save(t), includes);
    }

    public VIEW_DTO create(CREATE_DTO dto, List<String> includes) {
        return mapToDto(repository.save(mapForCreate(dto)), includes);
    }

    protected T mapForCreate(CREATE_DTO dto) {
        return dto.toModel();
    }

    protected abstract VIEW_DTO mapToDto(T t, List<String> includes);

    protected abstract T mapForUpdate(T t, CREATE_DTO dto);
}

package com.dylandewit.skeleton.resources;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<T extends BaseModel, VIEW_DTO extends BaseViewDto<T>, CREATE_DTO extends BaseCreateDto<T>> {
    protected final BaseService<T, VIEW_DTO, CREATE_DTO> service;

    public BaseController(BaseService<T, VIEW_DTO, CREATE_DTO> service) {
        this.service = service;
    }

    @GetMapping
    public Page<VIEW_DTO> findAll(@RequestParam(name = "ids", required = false) List<Long> ids, @RequestParam(name = "includes", required = false) List<String> includes, Pageable pageable) {
        if (ids != null) {
            return service.findByIds(ids, includes, pageable);
        }

        return service.findAll(includes, pageable);
    }

    @GetMapping("/{id}")
    public VIEW_DTO findById(@RequestParam(name = "includes", required = false) List<String> includes, @PathVariable Long id) {
        return service.findById(id, includes);
    }

    @PutMapping("/{id}")
    public VIEW_DTO update(@PathVariable Long id, @RequestBody @Valid CREATE_DTO dto, @RequestParam(name = "includes", required = false) List<String> includes) {
        return service.update(id, dto, includes);
    }

    @PostMapping
    public VIEW_DTO create(@RequestBody @Valid CREATE_DTO dto, @RequestParam(name = "includes", required = false) List<String> includes) {
        return service.create(dto, includes);
    }
}

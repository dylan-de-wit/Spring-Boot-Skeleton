package com.dylandewit.skeleton.api.base;

import com.dylandewit.skeleton.exception.response.ApiError;
import com.dylandewit.skeleton.api.base.dto.BaseCreateDto;
import com.dylandewit.skeleton.api.base.dto.BaseViewDto;
import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.api.base.models.OkResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiResponses(value = {
        @ApiResponse(code = 403, message = "Forbidden", response = ApiError.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = ApiError.class)
})
public abstract class BaseController<T extends BaseModel, VIEW_DTO extends BaseViewDto<T>, CREATE_DTO extends BaseCreateDto<T>> {
    protected final BaseService<T, VIEW_DTO, CREATE_DTO> service;

    public BaseController(BaseService<T, VIEW_DTO, CREATE_DTO> service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<VIEW_DTO> findAll(@RequestParam(name = "ids", required = false) List<Long> ids, @RequestParam(name = "includes", required = false) List<String> includes, Pageable pageable) {
        if (ids != null) {
            return service.findByIds(ids, includes, pageable);
        }

        return service.findAll(includes, pageable);
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public VIEW_DTO findById(@PathVariable Long id, @RequestParam(name = "includes", required = false) List<String> includes) {
        return service.findById(id, includes);
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VIEW_DTO update(@PathVariable Long id, @RequestBody @Valid CREATE_DTO body, @RequestParam(name = "includes", required = false) List<String> includes) {
        return service.update(id, body, includes);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public VIEW_DTO create(@RequestBody @Valid CREATE_DTO body, @RequestParam(name = "includes", required = false) List<String> includes) {
        return service.create(body, includes);
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OkResponse delete(@PathVariable Long id) {
        service.delete(id);

        return new OkResponse("... has been successfully deleted");
    }
}

package com.dylandewit.skeleton.api.base;

import com.dylandewit.skeleton.api.base.dto.BaseGetDto;
import com.dylandewit.skeleton.api.base.dto.BasePostDto;
import com.dylandewit.skeleton.api.base.models.BaseModel;
import com.dylandewit.skeleton.api.base.models.OkResponse;
import com.dylandewit.skeleton.exception.response.ApiError;
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
public abstract class BaseController<ENTITY extends BaseModel, GET_DTO extends BaseGetDto<ENTITY>, POST_DTO extends BasePostDto<ENTITY>> {
    protected final BaseService<ENTITY, GET_DTO, POST_DTO> service;

    public BaseController(BaseService<ENTITY, GET_DTO, POST_DTO> service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<GET_DTO> findAll(@RequestParam(name = "ids", required = false) List<Long> ids, Pageable pageable) {
        if (ids != null) {
            return service.findByIds(ids, pageable).map(service::mapToDto);
        }

        return service.findAll(pageable).map(service::mapToDto);
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GET_DTO findById(@PathVariable Long id) {
        return service.mapToDto(service.findById(id));
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GET_DTO update(@PathVariable Long id, @RequestBody @Valid POST_DTO body) {
        return service.mapToDto(service.update(id, body));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GET_DTO create(@RequestBody @Valid POST_DTO body) {
        return service.mapToDto(service.create(body));
    }

    @ApiResponse(code = 404, message = "Not found", response = ApiError.class)
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OkResponse delete(@PathVariable Long id) {
        service.delete(id);

        return new OkResponse("... has been successfully deleted");
    }
}

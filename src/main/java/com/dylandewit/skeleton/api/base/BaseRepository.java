package com.dylandewit.skeleton.api.base;

import com.dylandewit.skeleton.api.base.models.BaseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<T extends BaseModel> extends PagingAndSortingRepository<T, Long> {
    Set<T> findAllByIdIn(Iterable<Long> ids);

    Page<T> findAllByIdIn(Iterable<Long> ids, Pageable pageable);

    Set<T> findAll();
}

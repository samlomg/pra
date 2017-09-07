package com.dglbc.note.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Created by LbcLT on 2017/5/17.
 */


public interface BaseService<T> {


    List<T> findAll();
    List<T> findAll(Sort var1);
    List<T> findAll(Iterable var1);

    Page<T> findAll(Specification var1, Pageable var2);

    <S extends T> List<S> save(Iterable<S> var1);

    <S extends T> S save(S var1);
}

package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.Dweller;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <T, ID extends Serializable> extends Repository<T, ID> {

    Optional<T> save(T t);

    boolean exists(long id);

    void delete(long id);

    List<T> findAll();

    Optional<T> findOne(long id);
}

package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.DwellerImpl;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface DwellerRepository extends Repository <DwellerImpl, Long>{

    Optional<DwellerImpl> save(DwellerImpl dweller);

    void delete(long id);

    boolean exists(long id);

    List<DwellerImpl> findAll();
}

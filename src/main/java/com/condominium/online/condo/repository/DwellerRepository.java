package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.Dweller;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface DwellerRepository extends Repository <Dweller, Long>{

    Optional<Dweller> save(Dweller dweller);

    List<Dweller> findAll();
}

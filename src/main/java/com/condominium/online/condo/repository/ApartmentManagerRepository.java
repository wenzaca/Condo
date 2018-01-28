package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.ApartmentManager;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface ApartmentManagerRepository extends Repository<ApartmentManager,Long> {

    Optional<ApartmentManager> save(ApartmentManager apartmentManager);

}

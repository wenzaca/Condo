package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.ApartmentManagerImpl;
import org.springframework.data.repository.Repository;

import java.util.Optional;


public interface ApartmentManagerRepository extends Repository<ApartmentManagerImpl,Long> {

    Optional<ApartmentManagerImpl> save(ApartmentManagerImpl apartmentManager);

    void delete(long id);

    boolean exists(long id);

}

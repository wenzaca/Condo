package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.GuestImpl;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface GuestRepository extends Repository<GuestImpl, Long> {

    Optional<GuestImpl> save(GuestImpl guest);

}

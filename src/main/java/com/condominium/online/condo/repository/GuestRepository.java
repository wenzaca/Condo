package com.condominium.online.condo.repository;

import com.condominium.online.condo.entity.Guest;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface GuestRepository extends BaseRepository<Guest, Long> {
}

package com.eventapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.eventapp.models.Guest;

@Transactional
public interface GuestRepository extends CrudRepository<Guest, Long>{

}

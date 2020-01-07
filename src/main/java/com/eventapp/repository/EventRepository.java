package com.eventapp.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.eventapp.models.Event;

@Transactional
public interface EventRepository extends CrudRepository<Event, Long>{

}

package com.eventapp.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EventDao extends CrudRepository<Event, Long>{

}

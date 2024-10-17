package com.porkerspicks.ppbf.entities.repository;

import com.porkerspicks.ppbf.entities.Race;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends CrudRepository<Race, Long> {

}

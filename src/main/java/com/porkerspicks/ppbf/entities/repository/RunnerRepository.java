package com.porkerspicks.ppbf.entities.repository;

import com.porkerspicks.ppbf.entities.Race;
import com.porkerspicks.ppbf.entities.Runner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunnerRepository extends CrudRepository<Runner, Long> {

}

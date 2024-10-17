package com.porkerspicks.ppbf.entities.repository;

import com.porkerspicks.ppbf.entities.Horse;
import com.porkerspicks.ppbf.entities.Runner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Long> {

    public Horse findByName(String name);
}

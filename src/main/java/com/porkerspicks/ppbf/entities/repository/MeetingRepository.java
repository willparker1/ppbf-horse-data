package com.porkerspicks.ppbf.entities.repository;

import com.porkerspicks.ppbf.entities.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends CrudRepository<Meeting, Long> {

}

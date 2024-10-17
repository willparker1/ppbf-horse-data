package com.porkerspicks.ppbf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Meeting extends BaseEntity {

    @EqualsAndHashCode.Include
    private Integer meetingId;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;

    private Date meetingDate;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private Set<Race> races;

    public void addRace( Race race ) {
        this.races.add(race);
        race.setMeeting(this);
    }
}

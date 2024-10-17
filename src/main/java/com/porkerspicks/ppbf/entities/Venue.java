package com.porkerspicks.ppbf.entities;

import com.porkerspicks.ppbf.betfair.enums.CountryCode;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Venue extends BaseEntity{

    @EqualsAndHashCode.Include
    private String name;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    Set<Meeting> meetings;

    public void addMeeting( Meeting meeting ) {
            this.meetings.add(meeting);
            meeting.setVenue(this);
    }
}

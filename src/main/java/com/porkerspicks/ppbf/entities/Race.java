package com.porkerspicks.ppbf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Race extends BaseEntity {

    @EqualsAndHashCode.Include
    private String marketId;

    private String marketName;
    private Double distance;
    private long marketTime;
    private String raceType;
    //******* private Double totalMatched;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private Set<Runner> runners;

    public void addRunner(Runner runner) {
        this.runners.add(runner);
        runner.setRace(this);
    }
}

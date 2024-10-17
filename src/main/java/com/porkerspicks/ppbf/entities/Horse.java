package com.porkerspicks.ppbf.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Horse extends BaseEntity{

    @EqualsAndHashCode.Include
    private String name;

    private String sireName;
    private String damsireName;
    private String sexType;
    private String ownerName;
    private Integer damYearBorn;
    private String sireBred;
    private String damBred;
    private Integer sireYearBorn;
    private String damsireBred;
    private String bred;
    private String damName;
    private Integer damsireYearBorn;

    @OneToMany(mappedBy = "horse", cascade = CascadeType.ALL)
    private Set<Runner> runners;

    public void addRunner(Runner runner) {
        this.runners.add(runner);
        runner.setHorse(this);
    }
}

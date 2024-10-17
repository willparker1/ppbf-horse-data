package com.porkerspicks.ppbf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Runner extends BaseEntity{

    @EqualsAndHashCode.Include
    private Long runnerId;

    private Double handicap;
    private Integer officialRating;
    private Integer weightValue;
    private Integer daysSinceLastRun;
    private String wearing;
    private Byte clothNumber;
    private String jockeyName;
    private Integer jockeyClaim;
    private Integer adjustedRating;
    private String trainerName;
    private Integer age;
    private String form;
    private Integer forecastPriceDenominator;
    private Integer forecastPriceNumerator;
    private Integer stallDraw;
    private String weightUnits;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "horse_id")
    private Horse horse;
}

package com.porkerspicks.ppbf.betfair.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porkerspicks.ppbf.betfair.entities.Event;
import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import com.porkerspicks.ppbf.betfair.enums.CountryCode;
import com.porkerspicks.ppbf.entities.*;
import com.porkerspicks.ppbf.entities.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MarketCatalogueProcessor {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HorseRepository horseRepository;

    private static final String SIRE_NAME = "SIRE_NAME";
    private static final String OFFICIAL_RATING = "OFFICIAL_RATING";
    private static final String DAMSIRE_NAME = "DAMSIRE_NAME";
    private static final String WEIGHT_VALUE = "WEIGHT_VALUE";
    private static final String SEX_TYPE = "SEX_TYPE";
    private static final String DAYS_SINCE_LAST_RUN = "DAYS_SINCE_LAST_RUN";
    private static final String WEARING = "WEARING";
    private static final String OWNER_NAME = "OWNER_NAME";
    private static final String DAM_YEAR_BORN = "DAM_YEAR_BORN";
    private static final String SIRE_BRED = "SIRE_BRED";
    private static final String JOCKEY_NAME = "JOCKEY_NAME";
    private static final String DAM_BRED = "DAM_BRED";
    private static final String ADJUSTED_RATING = "ADJUSTED_RATING";
    private static final String SIRE_YEAR_BORN = "SIRE_YEAR_BORN";
    private static final String TRAINER_NAME = "TRAINER_NAME";
    private static final String AGE = "AGE";
    private static final String DAMSIRE_BRED = "DAMSIRE_BRED";
    private static final String FORM = "FORM";
    private static final String FORECASTPRICE_DENOMINATOR = "FORECASTPRICE_DENOMINATOR";
    private static final String FORECASTPRICE_NUMERATOR = "FORECASTPRICE_NUMERATOR";
    private static final String BRED = "BRED";
    private static final String DAM_NAME = "DAM_NAME";
    private static final String DAMSIRE_YEAR_BORN = "DAMSIRE_YEAR_BORN";
    private static final String STALL_DRAW = "STALL_DRAW";
    private static final String WEIGHT_UNITS = "WEIGHT_UNITS";

    public void process(Message<String> message) {
        try {
            List<MarketCatalogue> marketCatalogues = objectMapper.readValue(message.getPayload(), objectMapper.getTypeFactory().constructCollectionType(List.class, MarketCatalogue.class));
            transformData(marketCatalogues);
        } catch (IOException e) {
            // Handle JSON parsing errors
        }
    }

    public void transformData(List<MarketCatalogue> marketCatalogues) {

        Set<Meeting> meetings = new HashSet<>();

        marketCatalogues.forEach(marketCatalogue -> {
            Event event = marketCatalogue.getEvent();
            Integer meetingId = Integer.valueOf(event.getId());
            Meeting meeting = meetings.stream().filter(m -> m.getMeetingId().equals(meetingId)).findFirst().orElse(null);
            if (meeting == null) {
                meeting = new Meeting();
                meeting.setMeetingId(meetingId);
                meeting.setMeetingDate(event.getOpenDate());
                Venue venue = new Venue();
                venue.setName(event.getVenue());
                venue.setCountry(CountryCode.valueOf(event.getCountryCode()));
                meeting.setVenue(venue);
                meetings.add(meeting);
            }

            Race race = new Race();
            race.setMarketId(marketCatalogue.getMarketId());
            race.setMarketName(marketCatalogue.getMarketName());
            race.setDistance(extractDistance(marketCatalogue.getMarketName()));
            race.setRaceType(marketCatalogue.getDescription().getRaceType());
            //Not sure we need to hold this
            //race.setTotalMatched(marketCatalogue.getTotalMatched());
            meeting.addRace(race);

            marketCatalogue.getRunners().forEach(runnerCatalog -> {

                Runner runner = new Runner();
                runner.setOfficialRating(runnerCatalog.getMetadata().get(OFFICIAL_RATING) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(OFFICIAL_RATING)) : null);
                runner.setWeightValue(runnerCatalog.getMetadata().get(WEIGHT_VALUE) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(WEIGHT_VALUE)) : null);
                runner.setDaysSinceLastRun(runnerCatalog.getMetadata().get(DAYS_SINCE_LAST_RUN) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(DAYS_SINCE_LAST_RUN)) : null);
                runner.setWearing(runnerCatalog.getMetadata().get(WEARING));
                runner.setJockeyName(runnerCatalog.getMetadata().get(JOCKEY_NAME));
                runner.setAdjustedRating(runnerCatalog.getMetadata().get(ADJUSTED_RATING) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(ADJUSTED_RATING)) : null);
                runner.setTrainerName(runnerCatalog.getMetadata().get(TRAINER_NAME));
                runner.setAge(runnerCatalog.getMetadata().get(AGE) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(AGE)) : null);
                runner.setForm(runnerCatalog.getMetadata().get(FORM));
                runner.setForecastPriceDenominator(runnerCatalog.getMetadata().get(FORECASTPRICE_DENOMINATOR) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(FORECASTPRICE_DENOMINATOR)) : null);
                runner.setForecastPriceNumerator(runnerCatalog.getMetadata().get(FORECASTPRICE_NUMERATOR) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(FORECASTPRICE_NUMERATOR)) : null);
                runner.setStallDraw(runnerCatalog.getMetadata().get(STALL_DRAW) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(STALL_DRAW)) : null);
                runner.setWeightUnits(runnerCatalog.getMetadata().get(WEIGHT_UNITS));

                Horse horse = horseRepository.findByName(runnerCatalog.getRunnerName());
                if (horse == null) {
                    horse = new Horse();
                    horse.setName(runnerCatalog.getRunnerName());
                    horse.setSireName(runnerCatalog.getMetadata().get(SIRE_NAME));
                    horse.setDamsireName(runnerCatalog.getMetadata().get(DAMSIRE_NAME));
                    horse.setSexType(runnerCatalog.getMetadata().get(SEX_TYPE));
                    horse.setOwnerName(runnerCatalog.getMetadata().get(OWNER_NAME));
                    horse.setDamYearBorn(runnerCatalog.getMetadata().get(DAM_YEAR_BORN) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(DAM_YEAR_BORN)) : null);
                    horse.setSireBred(runnerCatalog.getMetadata().get(SIRE_BRED));
                    horse.setDamBred(runnerCatalog.getMetadata().get(DAM_BRED));
                    horse.setSireYearBorn(runnerCatalog.getMetadata().get(SIRE_YEAR_BORN) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(SIRE_YEAR_BORN)) : null);
                    horse.setDamsireBred(runnerCatalog.getMetadata().get(DAMSIRE_BRED));
                    horse.setBred(runnerCatalog.getMetadata().get(BRED));
                    horse.setDamName(runnerCatalog.getMetadata().get(DAM_NAME));
                    horse.setDamsireYearBorn(runnerCatalog.getMetadata().get(DAMSIRE_YEAR_BORN) != null ? Integer.valueOf(runnerCatalog.getMetadata().get(DAMSIRE_YEAR_BORN)) : null);
                }

                horse.addRunner(runner);
            });
        });
    }

    private double extractDistance(String raceName) {
        Pattern pattern = Pattern.compile("(\\d+)m(\\d*)f");
        Matcher matcher = pattern.matcher(raceName);
        if (matcher.find()) {
            int miles = Integer.parseInt(matcher.group(1));
            int furlongs = matcher.group(2).isEmpty() ? 0 : Integer.parseInt(matcher.group(2));
            return miles + (furlongs / 8.0);
        }
        throw new IllegalArgumentException("Invalid race name format");
    }
}
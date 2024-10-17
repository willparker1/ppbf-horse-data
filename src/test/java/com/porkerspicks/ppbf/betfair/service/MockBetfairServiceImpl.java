package com.porkerspicks.ppbf.betfair.service;

import com.porkerspicks.ppbf.betfair.entities.*;
import com.porkerspicks.ppbf.betfair.enums.MarketProjection;
import com.porkerspicks.ppbf.betfair.enums.MarketSort;
import com.porkerspicks.ppbf.betfair.enums.MatchProjection;
import com.porkerspicks.ppbf.betfair.enums.OrderProjection;
import com.porkerspicks.ppbf.file.FileStorage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

// Mock implementation class
@Service()
@Profile("test")
public class MockBetfairServiceImpl implements BetfairService {

    private final FileStorage fileStorage;

    @Autowired
    public MockBetfairServiceImpl(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @Override
    public List<EventTypeResult> listEventTypes(MarketFilter filter) {
        return fileStorage.readLatestFileForToday(EventTypeResult.class);
    }

    @Override
    public List<MarketCatalogue> listMarketCatalogue(MarketFilter filter, Set<MarketProjection> marketProjection,
                                                     MarketSort sort) {
        return fileStorage.readLatestFileForToday(MarketCatalogue.class);
    }

    @Override
    public List<MarketBook> listMarketBook(List<String> marketIds, PriceProjection priceProjection,
                                           OrderProjection orderProjection, MatchProjection matchProjection) {
        return fileStorage.readLatestFileForToday(MarketBook.class);
    }

    @Override
    public List<MarketBook> listRunnerBook(String marketId, long selectionId, PriceProjection priceProjection,
                                           OrderProjection orderProjection, MatchProjection matchProjection) {
        return fileStorage.readLatestFileForToday(MarketBook.class);
    }
}
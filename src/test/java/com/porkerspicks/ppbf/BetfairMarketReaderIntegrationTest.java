package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import com.porkerspicks.ppbf.betfair.service.BetfairMarketReader;
import com.porkerspicks.ppbf.file.FileStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class BetfairMarketReaderIntegrationTest {

    @Autowired
    private BetfairMarketReader betfairMarketReader;

    @Autowired
    private FileStorage fileStorage;

    @Test
    public void testGetTodaysMarketCatalogues() {
        // Act
        betfairMarketReader.getTodaysMarketCatalogues();

        // Assert
        List<MarketCatalogue> marketCatalogues = fileStorage.readTodaysMarketCatalogues();
        assertNotNull(marketCatalogues);
        assertNotEquals(0, marketCatalogues.size());
    }

    @Test
    public void testGetTodaysMarketBooks() {
        // Act
        betfairMarketReader.getTodaysMarketBooks();

        // Assert
//        List<MarketBook> marketBooks = fileStorage.readTodaysMarketBooks();
//        assertNotNull(marketBooks);
//        assertNotEquals(0, marketBooks.size());
    }

    @Test
    public void testGetTodaysRunnerBooks() {

        // Act
        betfairMarketReader.getRunnerBooks( Collections.singletonList("1.234420709") );

        // Assert
//        List<MarketBook> marketBooks = fileStorage.readTodaysMarketBooks();
//        assertNotNull(marketBooks);
//        assertNotEquals(0, marketBooks.size());
    }
}
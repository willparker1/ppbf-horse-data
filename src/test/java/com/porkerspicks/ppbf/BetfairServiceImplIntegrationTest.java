package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.betfair.entities.*;
import com.porkerspicks.ppbf.betfair.enums.*;
import com.porkerspicks.ppbf.betfair.service.BetfairServiceImpl;
import com.porkerspicks.ppbf.auth.Authenticator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@ActiveProfiles("test") // assuming you have a 'test' profile with test properties
public class BetfairServiceImplIntegrationTest {

    @Autowired
    private Authenticator authenticator;

    @Autowired
    private BetfairServiceImpl betfairServiceImpl;

    @Test
    public void testListEventTypes() {
        // Arrange
        MarketFilter filter = new MarketFilter();

        // Act
        List<EventTypeResult> result = betfairServiceImpl.listEventTypes(filter);

        // Assert
        assertNotNull(result);
        assertNotEquals(Collections.emptyList(), result);
    }

    @Test
    public void testListMarketBook() {
        // Arrange Market Book
        MarketFilter filter = new MarketFilter();
        PriceProjection priceProjection = new PriceProjection();
        OrderProjection orderProjection = OrderProjection.ALL;
        MatchProjection matchProjection = MatchProjection.NO_ROLLUP;

        // Act
        List<MarketCatalogue> marketCatalogue = getMarketCatalogue();

//        List<String> marketIds = new ArrayList<>();
//        for (MarketCatalogue market : marketCatalogue) {
//            marketIds.add(market.getMarketId()) ;
//        }
        List<String> marketIds = marketCatalogue.stream()
                .map(MarketCatalogue::getMarketId)
                .collect(Collectors.toList())
                        .subList(0, 1);

        List<MarketBook> result = betfairServiceImpl.listMarketBook(marketIds, priceProjection, orderProjection, matchProjection);

        // Assert
        assertNotNull(result);
        assertNotEquals(Collections.emptyList(), result);
    }

    @Test
    public void testListRunnerBook() {
        // Arrange Market Book
        PriceProjection priceProjection = new PriceProjection();
        Set<PriceData> priceDataList = new HashSet<PriceData>();
        priceDataList.add(PriceData.EX_ALL_OFFERS);
        priceProjection.setPriceData(priceDataList);
        OrderProjection orderProjection = OrderProjection.ALL;
        MatchProjection matchProjection = MatchProjection.NO_ROLLUP;

        // Act
        List<MarketCatalogue> marketCatalogue = getMarketCatalogue();

//        List<String> marketIds = new ArrayList<>();
//        for (MarketCatalogue market : marketCatalogue) {
//            marketIds.add(market.getMarketId()) ;
//        }
        List<String> marketIds = marketCatalogue.stream()
                .map(MarketCatalogue::getMarketId)
                .collect(Collectors.toList())
                .subList(0, 1);

        List<MarketBook> result = betfairServiceImpl.listRunnerBook("1", 1, priceProjection, orderProjection, matchProjection);

        // Assert
        assertNotNull(result);
        assertNotEquals(Collections.emptyList(), result);
    }

    @Test
    public void testListMarketCatalogue() {

        // Act
        List<MarketCatalogue> result = getMarketCatalogue();

        // Assert
        assertNotNull(result);
        assertNotEquals(Collections.emptyList(), result);
    }

    private List<MarketCatalogue> getMarketCatalogue() {
        MarketFilter filter = new MarketFilter();
        Set<MarketProjection> marketProjection = Collections.singleton(MarketProjection.EVENT);
        MarketSort sort = MarketSort.MAXIMUM_TRADED;
        return betfairServiceImpl.listMarketCatalogue(filter, marketProjection, sort);
    }
}
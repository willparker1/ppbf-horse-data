package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.azure.BlobStorage;
import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("local")
public class BlobStorageIntegrationTest {

    @Autowired
    private BlobStorage blobStorage;

    @Test
    public void testSaveMarketCatalogue() {
        // Create a sample MarketCatalogue
        MarketCatalogue marketCatalogue = new MarketCatalogue();
        marketCatalogue.setMarketId("1");
        marketCatalogue.setMarketName("Test Market");
        List<MarketCatalogue> marketCatalogues = new ArrayList<>();
        marketCatalogues.add(marketCatalogue);

        // Save the market catalogues
        blobStorage.saveMarketCatalogue(marketCatalogues);

        // Assert or verify the expected behavior
        // For example, check if the blob was created in Azurite
        // You can use Azurite's Explorer UI or SDKs to verify the blob existence
    }
}
package com.porkerspicks.ppbf.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class FileStorageTest {

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private FileStorage fileStorage;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveList() throws Exception {
        MarketCatalogue marketCatalogue = new MarketCatalogue();
        String jsonResponse = "[{\"marketId\":\"1\"}]";

        when(objectMapper.writeValueAsString(Collections.singletonList(marketCatalogue))).thenReturn(jsonResponse);

        fileStorage.saveList(Collections.singletonList(marketCatalogue));

        String filePath = fileStorage.getDirectoryPath(MarketCatalogue.class) + "\\" + fileStorage.getFileName();

        verify(objectMapper, times(1)).writeValueAsString(Collections.singletonList(marketCatalogue));
        assertTrue(Files.exists(Paths.get(filePath)));
    }

    @Test
    public void testReadList_ValidFile() throws Exception {
        String jsonResponse = "[{\"marketId\":\"1\"}]";
        List<MarketCatalogue> expectedList = Collections.singletonList(new MarketCatalogue());

        String filePath = fileStorage.getDirectoryPath(MarketCatalogue.class) + "\\" + fileStorage.getFileName();

        when(Files.readString(Paths.get(filePath))).thenReturn(jsonResponse);
        when(objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, MarketCatalogue.class)))
                .thenReturn(expectedList);

        List<MarketCatalogue> result = fileStorage.readTodaysMarketCatalogues();

        assertNotNull(result);
        assertEquals(expectedList, result);
    }

    @Test
    public void testReadList_IOException() throws Exception {
        String filePath = fileStorage.getDirectoryPath(MarketCatalogue.class) + "\\" + fileStorage.getFileName();

        when(Files.readString(Paths.get(filePath))).thenThrow(new IOException("File read error"));

        List<MarketCatalogue> result = fileStorage.readTodaysMarketCatalogues();

        assertNull(result);
    }

    @Test
    public void testReadList_EmptyDirectory() throws Exception {
        String directoryPath = fileStorage.getDirectoryPath(MarketCatalogue.class);

        when(Files.list(Paths.get(directoryPath))).thenReturn(Stream.empty());

        List<MarketCatalogue> result = fileStorage.readTodaysMarketCatalogues();

        assertNull(result);
    }
}
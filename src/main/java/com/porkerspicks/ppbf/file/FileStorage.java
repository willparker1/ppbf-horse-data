package com.porkerspicks.ppbf.file;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.porkerspicks.ppbf.betfair.entities.MarketBook;
import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
import com.porkerspicks.ppbf.betfair.entities.Runner;
import com.porkerspicks.ppbf.betfair.entities.RunnerCatalog;
import de.codecentric.boot.admin.server.web.client.InstanceExchangeFilterFunction;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class FileStorage {

    FileStorageConfig fileStorageConfig;

    ObjectMapper objectMapper = new ObjectMapper();

    public FileStorage(FileStorageConfig fileStorageConfig) {
        this.fileStorageConfig = fileStorageConfig;
    }

    public void saveList(List<?> objects) {
        saveList("", objects);
    }

    public void saveList(String id, List<?> objects) {

        // Convert the MarketCatalogue object to a JSON string
        String jsonResponse = null;
        try {
            jsonResponse = objectMapper.writeValueAsString(objects);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // Save the JSON string to File System
        if (jsonResponse != null) {
            saveJsonToFileSystem(id, jsonResponse, getListElementType( objects ) );
        }
    }

    private void saveJsonToFileSystem(String id, String jsonResponse, Class<?> clazz ) {

        // Define the path to the file

        String directoryPath = getDirectoryPath(id, clazz);
        String filePath =  directoryPath + "\\" +getFileName();

        // Create the directory if it does not exist
        try {
            Files.createDirectories(Paths.get(directoryPath));
        } catch (IOException e) {
            log.error("Failed to create directory: " + e.getMessage());
            return;
        }

        // Write the JSON response to a ZIP file
        try {
            Files.write(Paths.get(filePath), jsonResponse.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String readJsonFromFileSystem(String filePath) {

        String jsonResponse = null;
        // Read the JSON response from the ZIP file
        try {
            jsonResponse = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return jsonResponse;
    }

    public List<MarketCatalogue> readTodaysMarketCatalogues() {
        return readLatestFileForToday(MarketCatalogue.class);
    }

    public List<MarketBook> readTodaysMarketBooks() {
        return readLatestFileForToday(MarketBook.class);
    }

    public <T> List<T> readLatestFileForToday(Class<T> clazz) {
        return readLatestFileForToday("", clazz);
    }

    public <T> List<T> readLatestFileForToday(String id, Class<T> clazz) {

        String directoryPath = getDirectoryPath(id, clazz);

        Optional<Path> latestFilePath = findLatestFile(directoryPath);

        if (latestFilePath.isPresent()) {
            String jsonResponse = readJsonFromFileSystem(latestFilePath.get().toString());

            List<T> objects = null;
            try {
                objects = objectMapper.readValue(jsonResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            return objects;
        } else {
            log.error("No files found in directory: " + directoryPath);
            return null;
        }
    }

    private Optional<Path> findLatestFile(String directoryPath) {
        try {
            return Files.list(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .max(Comparator.comparingLong(p -> p.toFile().lastModified()));
        } catch (IOException e) {
            log.error("Failed to list files in directory: " + e.getMessage());
            return Optional.empty();
        }
    }

    public String getFileName() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(fileStorageConfig.getTimeFormat())) + ".json";
    }

    public String getDirectoryPath(Class<?> clazz) {
        return getDirectoryPath("", clazz);
    }

    public String getDirectoryPath(String id, Class<?> clazz) {
        return fileStorageConfig.getBasePath() +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(fileStorageConfig.getDateFormat())) + "\\" +
                clazz.getSimpleName() + "\\" +
                id;
    }

    private static <T> Class<?> getListElementType(List<T> list) {
        if (!list.isEmpty()) {
            return list.get(0).getClass();
        } else {
            return Object.class;
        }
    }
}
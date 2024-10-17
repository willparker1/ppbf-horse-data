package com.porkerspicks.ppbf.config;

    import com.porkerspicks.ppbf.betfair.data.MarketCatalogueProcessor;
    import com.porkerspicks.ppbf.betfair.entities.MarketCatalogue;
    import com.porkerspicks.ppbf.file.FileStorage;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

            import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;

            import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;

            import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.AcceptOnceFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;


            import java.io.File;
    import java.time.LocalDateTime;
    import java.time.format.DateTimeFormatter;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class SpringIntegrationConfig {

    @Autowired
    private MarketCatalogueProcessor marketCatalogueProcessor;

    @Autowired
    private FileStorage fileStorage;

    public MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    public MessageSource<File> fileReader( Class<?> clazz ) {

        String directoryPath = fileStorage.getDirectoryPath(clazz);

        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setDirectory(new File(directoryPath));
        // Adjust the path
        source.setFilter(new AcceptOnceFileListFilter<>());
        source.setUseWatchService(true); // Optional: Monitor for new files
        return source;
    }

    @Bean
    public IntegrationFlow processJsonFiles() {
        return IntegrationFlow.from(fileReader( MarketCatalogue.class ),
                        sourcePollingChannelAdapterSpec ->
                                sourcePollingChannelAdapterSpec.poller(Pollers.fixedDelay(5000))) // Poll every 5 seconds
                .transform(new FileToStringTransformer())
                .channel(fileInputChannel())
                .handle(marketCatalogueProcessor)
                .get();
    }
}


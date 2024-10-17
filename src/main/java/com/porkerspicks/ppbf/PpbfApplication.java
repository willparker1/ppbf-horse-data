package com.porkerspicks.ppbf;

import com.porkerspicks.ppbf.file.FileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication(scanBasePackages = {"com.porkerspicks","com.betfair"} )
@ConfigurationPropertiesScan("com.porkerspicks.ppbf")
public class PpbfApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpbfApplication.class, args);
    }

}

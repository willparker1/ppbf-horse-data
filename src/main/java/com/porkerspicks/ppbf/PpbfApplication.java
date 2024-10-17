package com.porkerspicks.ppbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.porkerspicks"} )
@ConfigurationPropertiesScan("com.porkerspicks.ppbf")
public class PpbfApplication {

    public static void main(String[] args) {
        SpringApplication.run(PpbfApplication.class, args);
    }

}

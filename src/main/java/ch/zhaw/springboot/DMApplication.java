package ch.zhaw.springboot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import data.CSVDataService;

// Hauptklasse für die DM-Applikation
@SpringBootApplication
@ComponentScan(basePackages = {"ch.zhaw.springboot", "data"}) // Add the package name of CSVDataService here
public class DMApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DMApplication.class, args);

        // Get the CSVDataService bean from the application context
        CSVDataService csvDataService = context.getBean(CSVDataService.class);

        try {
            csvDataService.loadCSVData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

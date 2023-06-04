package ch.zhaw.springboot;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import ch.zhaw.springboot.data_export.mongo_export;
import data.CSVDataService;

// Main class for DM-Applikation
@SpringBootApplication
@ComponentScan(basePackages = { "ch.zhaw.springboot", "data" })
public class DMApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DMApplication.class, args);

        // Load CSVDataService bean from the app
        CSVDataService csvDataService = context.getBean(CSVDataService.class);

        try {
            csvDataService.loadCSVData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load mongo_export
        mongo_export exporter = context.getBean(mongo_export.class);
        exporter.importDataFromRepository();
        exporter.exportDataToCSV();

        context.close();
    }
}

package data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import ch.zhaw.springboot.entities.Experience;
import ch.zhaw.springboot.entities.Route;
import ch.zhaw.springboot.entities.Tourist;
import ch.zhaw.springboot.entities.Trip;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVDataService {
    private final ResourceLoader resourceLoader;
    private static final Logger logger = LogManager.getLogger(CSVDataService.class);

    public CSVDataService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<Trip> loadCSVData() throws IOException {
        List<Trip> trips = new ArrayList<>();

        // Load the CSV file from the specified folder structure
        Resource resource = resourceLoader.getResource("classpath:test/resources/dm_data_csv/data.csv");

        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(reader);

            // Process each CSV record
            for (CSVRecord record : csvParser) {
                String touristName = record.get("tourist_name");
                String touristNationality = record.get("tourist_nationality");
                String duration = record.get("duration");
                String tripDestination = record.get("trip_destination");
                String tripDistance = record.get("trip_distance");

                // Create a Tourist object
                Tourist tourist = new Tourist(touristName, touristNationality);

                // Create an Experience object
                Experience experience = new Experience(duration);

                // Create a Route object
                Route trip = new Route(tripDestination, tripDistance);

                trips.add(trip);

                // Generate SQL insert statement
                generateInsertStatement(tourist, experience, trip);
            }
        }

        return trips;
    }

    private void generateInsertStatement(Tourist tourist, Experience experience, Route trip) {

        String touristInsertStatement = "INSERT INTO tourist (name, nationality) VALUES ('" + tourist.getName() + "', '"
                + tourist.getNationality() + "');";
        String experienceInsertStatement = "INSERT INTO experience VALUES ('" + experience.getDuration() + "');";
        String tripInsertStatement = "INSERT INTO trips (destination, distance) VALUES ('" + trip.getDestination()
                + "', '" + trip.getDistance() + "');";

        // Save to SQL files
        try (PrintWriter writer = new PrintWriter(new FileWriter("TouristInsert.sql"))) {
            writer.println(touristInsertStatement);
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file.", e); // logger method to catch error within the
                                                                             // framework Log4j: why? provides more
                                                                             // flexibility & control over logging
                                                                             // compared to printing stack traces with
                                                                             // e.printStackTrace() /allows to
                                                                             // configure log levels, log destinations,
                                                                             // & format log messages
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("ExperienceInsert.sql"))) {
            writer.println(experienceInsertStatement);
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file.", e);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("TripInsert.sql"))) {
            writer.println(tripInsertStatement);
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file.", e);
        }
    }
}
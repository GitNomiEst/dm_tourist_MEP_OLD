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

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVDataService {
    private final ResourceLoader resourceLoader;

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

        String touristinsertStatement = "INSERT INTO tourist (name) VALUES ('" + tourist.getName() + "');";
        String touristinsertStatement1 = "INSERT INTO tourist (nationality) VALUES ('" + tourist.getNationality() + "');";
        String experienceinsertStatement = "INSERT INTO experience VALUES ('" + experience.getDuration() + "');";
        String tripinsertStatement = "INSERT INTO trips VALUES ('" + trip.getDestination() + "');";
        String tripinsertStatement1 = "INSERT INTO trips VALUES ('" + trip.getDistance() + "');";

        // Output
        System.out.println(touristinsertStatement);
        System.out.println(touristinsertStatement1);
        System.out.println(experienceinsertStatement);
        System.out.println(tripinsertStatement);
        System.out.println(tripinsertStatement1);
    }   
}
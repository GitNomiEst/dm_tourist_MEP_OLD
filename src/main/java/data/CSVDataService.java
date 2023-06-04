package data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import ch.zhaw.springboot.entities.Experience;
import ch.zhaw.springboot.entities.Route;
import ch.zhaw.springboot.entities.Tourist;
import ch.zhaw.springboot.repositories.ExperienceRepository;
import ch.zhaw.springboot.repositories.TouristRepository;
import ch.zhaw.springboot.repositories.TripRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

@Service
public class CSVDataService {
    private final ResourceLoader resourceLoader;
    private static final Logger logger = LogManager.getLogger(CSVDataService.class);

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private TouristRepository touristRepository;

    public CSVDataService(ResourceLoader resourceLoader, TripRepository tripRepository,
            ExperienceRepository experienceRepository, TouristRepository touristRepository) {
        this.resourceLoader = resourceLoader;
        this.tripRepository = tripRepository;
        this.experienceRepository = experienceRepository;
        this.touristRepository = touristRepository;
    }

    public void loadCSVData() throws IOException {

        // Load the CSV file from the specified folder structure
        Resource resource = resourceLoader.getResource("classpath:dm_data.csv");

        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(reader);

            // Process each CSV record
            for (CSVRecord record : csvParser) {
                String touristName = record.get("Tourist_name");
                String touristNationality = record.get("Tourist_nationality");
                String duration = record.get("Duration_inDays");
                String tripDestination = record.get("Destination");
                String tripDistance = record.get("Route_distance");

                // Create a Tourist object
                Tourist tourist = new Tourist(touristName, touristNationality);

                // Create an Experience object
                Experience experience = new Experience(duration);

                // Create a Route object
                Route trip = new Route(tripDestination, tripDistance);

                // Generate SQL insert statement
                generateInsertStatement(tourist, experience, trip);

                tripRepository.save(trip);
                experienceRepository.save(experience);
                touristRepository.save(tourist);

            }
        }
    }

    private void generateInsertStatement(Tourist tourist, Experience experience, Route trip) {

        // Define insert statements
        String touristInsertStatement = "INSERT INTO tourist (name, nationality) VALUES ('" + tourist.getName() + "', '"
                + tourist.getNationality() + "');";
        String experienceInsertStatement = "INSERT INTO experience VALUES ('" + experience.getDuration() + "');";
        String tripInsertStatement = "INSERT INTO trips (destination, distance) VALUES ('" + trip.getDestination()
                + "', '" + trip.getDistance() + "');";

        // Save to SQL seperate files. PrintWriter & FileWriter are used to write the
        // insert statements to the respective files.
        try (PrintWriter writer = new PrintWriter(new FileWriter("TouristInsert.sql"))) {
            writer.println(touristInsertStatement);
        } catch (IOException e) {
            logger.error("An error occurred while writing to the file.", e);
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
    // Catch errors with framework Log4j -why? provides more flexibility and control
    // over logging compared to printing stack traces with e.printStackTrace()
    // allows to configure log levels, log destinations & add format log messages

    public Object getLogger() {
        return null;
    }
}
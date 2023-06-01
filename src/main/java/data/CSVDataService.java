package data;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

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

    public List<TripData> loadCSVData() throws IOException {
        List<TripData> tripDataList = new ArrayList<>();
        
        // Load the CSV file from the specified folder structure
        Resource resource = resourceLoader.getResource("classpath:test/resources/dm_data_csv/data.csv");

        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            CSVParser csvParser = CSVFormat.DEFAULT.withHeader<>.parse(reader);
            
            // Process each CSV record
            for (CSVRecord record : csvParser) {
                String touristName = record.get("tourist_name");
                String touristNationality = record.get("tourist_nationality");
                String duration = record.get("duration");
                String tripDestination = record.get("trip_destination");
                String tripDistance = record.get("trip_distance");
                
                // Create a TripData object and add it to the list
                TripData tripData = new TripData(touristName, touristNationality, duration, tripDestination, tripDistance);
                tripDataList.add(tripData);
            }
        }

        return tripDataList;
    }
}

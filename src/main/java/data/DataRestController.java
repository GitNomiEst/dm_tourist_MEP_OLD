package data;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

public class DataRestController {
    
    private final CSVDataService csvDataService;

    public DataRestController(CSVDataService csvDataService) {
        this.csvDataService = csvDataService;
    }

    @GetMapping("/data")
    public List<TripData> getData() throws IOException {
        return csvDataService.loadCSVData();
    }
}

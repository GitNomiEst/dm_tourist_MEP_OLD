package data;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import ch.zhaw.springboot.entities.Trip;

public class DataRestController {
    
    private final CSVDataService csvDataService;

    public DataRestController(CSVDataService csvDataService) {
        this.csvDataService = csvDataService;
    }

    @GetMapping("/data")
    public List<Trip> getData() throws IOException {
        return csvDataService.loadCSVData();
    }
}

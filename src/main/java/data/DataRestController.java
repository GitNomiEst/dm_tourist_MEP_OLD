package data;

public class DataRestController {

    private final CSVDataService csvDataService;

    public DataRestController(CSVDataService csvDataService) {
        this.csvDataService = csvDataService;
    }

}


/*package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class DataRestController {

    private final CSVDataService csvDataService;

    @Autowired
    public DataRestController(CSVDataService csvDataService) {
        this.csvDataService = csvDataService;
    }

    @GetMapping("/load-csv")
    public String loadCSVData() {
        try {
            csvDataService.loadCSVData();
            return "CSV data loaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to load CSV data";
        }
    }
}*/

package ch.zhaw.springboot.data_export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

import ch.zhaw.springboot.entities.Tourist;
import ch.zhaw.springboot.repositories.TouristRepository;

@Component
public class mongo_export {
    private final String nodesCsvFile = "nodes.csv";
    private final String edgesCsvFile = "edges.csv";

    private ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://kaeseno1:eXuzhJ-ZV6KUH4t@cluster0.4pnoho7.mongodb.net/?retryWrites=true&w=majority");
    private MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    private MongoClient mongoClient = MongoClients.create(settings);
    private MongoDatabase database = mongoClient.getDatabase("tourist");
    private MongoCollection<Document> edgesCol = database.getCollection("edges");
    private MongoCollection<Document> nodesCol = database.getCollection("nodes");

    @Autowired
    private TouristRepository touristRepository;

    public void importDataFromRepository() {
        List<Tourist> tourists = touristRepository.findAll();

        if (tourists != null) {
            // Convert the Tourist objects to MongoDB Documents
            List<Document> nodes = new ArrayList<>();
            List<Document> edges = new ArrayList<>();

            for (Tourist tourist : tourists) {
                Document node = new Document("id", tourist.getId())
                    .append("name", tourist.getName());
                nodes.add(node);

                List<Tourist> relatedTourists = touristRepository.findTouristsByTripId(tourist.getTrip().getId());
                for (Tourist relatedTourist : relatedTourists) {
                    Document edge = new Document("source", tourist.getId())
                        .append("target", relatedTourist.getId());
                    edges.add(edge);
                }
            }

            // Insert the documents into the MongoDB collections
            InsertManyOptions options = new InsertManyOptions().ordered(false);
            nodesCol.insertMany(nodes, options);
            edgesCol.insertMany(edges, options);
        }
    }

    public void exportDataToCSV() {
        // Query MongoDB to get the list of nodes
        FindIterable<Document> nodes = nodesCol.find();

        // Output nodes to CSV file
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(nodesCsvFile), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id", "name");

            for (Document node : nodes) {
                csvPrinter.printRecord(
                    node.get("id"),
                    node.get("name"));
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Query MongoDB to get the list of edges
        FindIterable<Document> edges = edgesCol.find();

        // Output edges to CSV file
        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(edgesCsvFile), CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("source", "target");
            for (Document edge : edges) {
                csvPrinter.printRecord(
                    edge.get("source"),
                    edge.get("target"));
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        mongo_export exporter = context.getBean(mongo_export.class);
        exporter.importDataFromRepository();
        exporter.exportDataToCSV();
    }

}

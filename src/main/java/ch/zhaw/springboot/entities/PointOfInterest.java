package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PointOfInterest extends Trip {

    private String category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public PointOfInterest(String destination, String category) {
        super(destination);
        this.category = category;
    }

    public PointOfInterest() {

    }

    public String getCategory() {
        return category;
    }
}

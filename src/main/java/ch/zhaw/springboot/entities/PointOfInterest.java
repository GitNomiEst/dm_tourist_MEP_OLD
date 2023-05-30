package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;

@Entity
public class PointOfInterest extends Trip {

    private String category;

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

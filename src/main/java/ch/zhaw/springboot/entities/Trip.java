package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;

@Entity
public class Trip {

    private String destination;

    public Trip(String destination) {
        this.destination = destination;
    }

    public Trip() {
        
    }

    public String getDestination() {
        return destination;
    }
}

package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

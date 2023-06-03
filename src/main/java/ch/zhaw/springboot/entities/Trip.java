package ch.zhaw.springboot.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

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

package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Route extends Trip {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private Trip trip;

    private String distance;

    public Route(String destination, String distance) {
        super(destination);
        this.distance = distance;
    }

    public Route() {

    }

    public String getDistance() {
        return distance;
    }   
}

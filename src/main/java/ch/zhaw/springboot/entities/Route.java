package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;

@Entity
public class Route extends Trip {
    
    private long distance;

    public Route(String destination, long distance) {
        super(destination);
        this.distance = distance;
    }

    public Route() {

    }

    public long getDistance() {
        return distance;
    }   
}

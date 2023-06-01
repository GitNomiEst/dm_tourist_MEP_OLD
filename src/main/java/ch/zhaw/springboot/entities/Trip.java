package ch.zhaw.springboot.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Trip {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private List<Experience> experienceList = new ArrayList<>();

    @OneToMany
    private Experience experience;

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

package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tourist {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; 
    private String country;

    public Tourist(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Tourist() {

    }

    public String getName() {
        return name;
    }
    public String getCountry() {
        return country;
    }
}

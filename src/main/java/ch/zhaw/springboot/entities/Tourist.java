package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;

@Entity
public class Tourist {

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

package ch.zhaw.springboot.entities;

import com.mongodb.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Tourist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Trip trip;

    private String name;
    private String nationality;

    public Tourist(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public Tourist() {

    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public long getId() {
        return id;
    }

    @Nullable 
    public Trip getTrip() {
        return trip;
    }


}

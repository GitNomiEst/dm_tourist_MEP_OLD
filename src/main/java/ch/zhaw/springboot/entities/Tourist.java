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
}

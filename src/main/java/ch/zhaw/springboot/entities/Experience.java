package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Experience {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Tourist tourist;

    private String duration;

    public Experience(String duration) {
        this.duration = duration;
    }

    public Experience() {
        
    }

    public String getDuration() {
        return duration;
    }
}

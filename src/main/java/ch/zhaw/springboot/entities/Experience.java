package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Experience {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long duration;

    public Experience(long duration) {
        this.duration = duration;
    }

    public Experience() {
        
    }

    public long getDuration() {
        return duration;
    }
}

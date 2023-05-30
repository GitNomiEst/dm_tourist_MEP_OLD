package ch.zhaw.springboot.entities;

import jakarta.persistence.Entity;

@Entity
public class Experience {

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

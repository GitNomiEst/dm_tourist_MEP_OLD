package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.Experience;

public interface ExperienceRepository extends JpaRepository<Experience, Long>{
 
    @Query("SELECT e FROM Experience e WHERE e.id = ?1")
    public List<Experience> findExperienceByID(Long id);

    @Query("SELECT e FROM Experience e WHERE e.duration = ?1")
    public List<Experience> findExperienceByDuration(Long duration);
    
}

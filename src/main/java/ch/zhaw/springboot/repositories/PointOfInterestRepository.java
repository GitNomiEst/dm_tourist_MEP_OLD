package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, String> {

    @Query("SELECT i FROM PointOfInterest i WHERE i.id = ?1")
    public List<PointOfInterest> findPointOfInterestByID(Long id);

    @Query("SELECT i FROM PointOfInterest i WHERE i.category = ?1")
    public List<PointOfInterest> findPointOfInterestByCategory(String category);

}

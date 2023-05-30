package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import ch.zhaw.springboot.entities.Tourist;

public class TouristRepository extends JpaRepository<Tourist, String> {

    @Query("SELECT t FROM Tourist t WHERE t.name = ?1")
    public List<Tourist> findTouristByName(String name);

    @Query("SELECT t FROM Tourist t WHERE t.country = ?1")
    public List<Tourist> findTouristByCountry(String country);
    
}

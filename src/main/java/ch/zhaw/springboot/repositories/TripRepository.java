package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, String> {

    @Query("SELECT t FROM Trip t WHERE t.id = ?1")
    public List<Trip> findTripByID(Long id);

    @Query("SELECT t FROM Trip t WHERE t.destination = ?1")
    public List<Trip> findTripByDestination(String destination);

}
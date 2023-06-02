package ch.zhaw.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ch.zhaw.springboot.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE r.id = ?1")
    public List<Route> findRouteByID(Long id);

    @Query("SELECT r FROM Route r WHERE r.distance = ?1")
    public List<Route> findRouteByDistance(String distance);

    @Query("SELECT r FROM Route r WHERE r.destination = ?1")
    public List<Route> findRouteByDestination(String destination);

}

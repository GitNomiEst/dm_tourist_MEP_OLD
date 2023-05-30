package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Trip;
import ch.zhaw.springboot.repositories.TripRepository;

@RestController
public class TripRestController {

    @Autowired
	private TripRepository repository;

	@RequestMapping(value = "travel/trip", method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getTrip() {
		List<Trip> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Trip>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trip>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/trip/{destination}", method = RequestMethod.GET)
	public ResponseEntity<List<Trip>> getTripByDestination(@PathVariable("Destination") String destination) {
		List<Trip> result = this.repository.findTripByDestination(destination);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Trip>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Trip>>(HttpStatus.NOT_FOUND);
		}
	}    
}

package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.PointOfInterest;
import ch.zhaw.springboot.repositories.PointOfInterestRepository;

@RestController
public class PointOfInterestRestController {

	@Autowired
	private PointOfInterestRepository repository;

	@RequestMapping(value = "travel/interest", method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> getPointOfInterest() {
		List<PointOfInterest> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<PointOfInterest>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PointOfInterest>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/interset/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> getPointOfInterestByID(@PathVariable("ID") Long id) {
		List<PointOfInterest> result = this.repository.findPointOfInterestByID(id);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<PointOfInterest>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PointOfInterest>>(HttpStatus.NOT_FOUND);
		}
	}
}

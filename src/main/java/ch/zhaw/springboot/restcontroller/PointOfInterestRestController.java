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

	@RequestMapping(value = "travel/pointOfInterest", method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> getPointOfInterest() {
		List<PointOfInterest> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<PointOfInterest>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PointOfInterest>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/PointOfInterest/{category}", method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> getPointOfInterestByCategory(@PathVariable("Category") String category) {
		List<PointOfInterest> result = this.repository.findPointOfInterestByCategory(category);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<PointOfInterest>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<PointOfInterest>>(HttpStatus.NOT_FOUND);
		}
	}    
}

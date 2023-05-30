package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Tourist;
import ch.zhaw.springboot.repositories.TouristRepository;

@RestController
public class TouristRestController {

    @Autowired
	private TouristRepository repository;

	@RequestMapping(value = "travel/tourist", method = RequestMethod.GET)
	public ResponseEntity<List<Tourist>> getTourist() {
		List<Tourist> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Tourist>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Tourist>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/tourist/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Tourist>> getTouristByName(@PathVariable("Name") String name) {
		List<Tourist> result = this.repository.findTouristByName(name);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Tourist>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Tourist>>(HttpStatus.NOT_FOUND);
		}
	}    
}
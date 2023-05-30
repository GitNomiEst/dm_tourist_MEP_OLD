package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Experience;
import ch.zhaw.springboot.repositories.ExperienceRepository;

@RestController
public class ExperienceRestController {

    @Autowired
	private ExperienceRepository repository;

	@RequestMapping(value = "travel/experience", method = RequestMethod.GET)
	public ResponseEntity<List<Experience>> getExperience() {
		List<Experience> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Experience>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Experience>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/experience/{duration}", method = RequestMethod.GET)
	public ResponseEntity<List<Experience>> getExperienceByDuration(@PathVariable("Duration") long duration) {
		List<Experience> result = this.repository.findExperienceByDuration(duration);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Experience>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Experience>>(HttpStatus.NOT_FOUND);
		}
	}    
}

package ch.zhaw.springboot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.springboot.entities.Route;
import ch.zhaw.springboot.repositories.RouteRepository;

@RestController
public class RouteRestController {

	@Autowired
	private RouteRepository repository;

	@RequestMapping(value = "travel/route", method = RequestMethod.GET)
	public ResponseEntity<List<Route>> getRoute() {
		List<Route> result = this.repository.findAll();

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Route>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Route>>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "travel/route/{ID}", method = RequestMethod.GET)
	public ResponseEntity<List<Route>> getRouteByID(@PathVariable("ID") Long ID) {
		List<Route> result = this.repository.findRouteByID(ID);

		if (!result.isEmpty()) {
			return new ResponseEntity<List<Route>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Route>>(HttpStatus.NOT_FOUND);
		}
	}
}

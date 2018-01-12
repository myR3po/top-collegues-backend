package dev.topcollegues.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "/check")
public class AliveController {

	@GetMapping
	public ResponseEntity<String> isAlive() {
		
		return new ResponseEntity<String>("Hello World", HttpStatus.OK);
	}
}

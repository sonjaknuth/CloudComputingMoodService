package moodService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class MoodController {
	@RequestMapping(value ="/", method = RequestMethod.POST)
	public ResponseEntity<?> setMood(@RequestBody Mood input) throws IOException {
		System.out.println(input.toString());
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

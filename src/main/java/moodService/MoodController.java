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
	@RequestMapping(value = "/hueservice/mood", method = RequestMethod.POST)
	public ResponseEntity<?> setMood(@RequestBody Mood input) throws IOException {
		if (input == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		// Do something with the mood --> Change color
		System.out.println(input.toString());
		return new ResponseEntity<>(HttpStatus.OK);

	}

}

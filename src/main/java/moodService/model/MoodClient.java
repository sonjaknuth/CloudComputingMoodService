package moodService.model;

import feign.RequestLine;

public interface MoodClient {
	 @RequestLine("GET /api/hueservice/mood")
	 String getMood();
}

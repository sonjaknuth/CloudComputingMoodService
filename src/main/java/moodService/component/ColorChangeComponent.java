package moodService.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import feign.Feign;
import feign.gson.GsonDecoder;
import moodService.constants.MoodColor;
import moodService.model.HueClient;
import moodService.model.MoodClient;

@Component
public class ColorChangeComponent {
	private static final String CLOUD_APP_BASE_ADDRESS = "https://w4p-moodservice.eu-gb.mybluemix.net/";
	private static final String IP = "192.168.2.110";
	private static final String HUE_BASE_ADDRESS = "http://"+IP;
	

	static String mood = null;
	
	private final static Map<String, MoodColor> moodColorStore = new HashMap<>();
    static{
	   moodColorStore.put("Anger", MoodColor.RED);
	   moodColorStore.put("Disgust", MoodColor.GREEN);
	   moodColorStore.put("Fear", MoodColor.VIOLET);
	   moodColorStore.put("Joy", MoodColor.YELLOW);
	   moodColorStore.put("Sadness", MoodColor.BLUE);
    }


	@Scheduled(fixedRate = 9000)
	public void pollForMood() {
		MoodClient resource = Feign.builder().decoder(new GsonDecoder())
				.target(MoodClient.class, CLOUD_APP_BASE_ADDRESS);
		String currentMood = resource.getMood();
		System.out.println(currentMood);
		if (currentMood != null) {			
			if (!currentMood.equals(mood) && moodColorStore.containsKey(currentMood)) {
				System.out.println("-----Mood  Changed-----");
				mood = currentMood;	
				MoodColor color = moodColorStore.get(mood);

				HueClient hueClient = Feign.builder().decoder(new GsonDecoder())
						.target(HueClient.class, HUE_BASE_ADDRESS); 
				hueClient.changeColor("qRayC2laYNSExVpoNMQCYxA1WwybESLBKGRe717M","1",color.getX(), color.getY());
			}
		}
	}
}

package moodService;

public class Mood {
	private String mood;

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}
	
	@Override
	public String toString(){
		return mood;
	}
		
}

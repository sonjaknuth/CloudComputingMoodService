package moodService.constants;

public enum MoodColor {
	RED(0.675,0.322),
	GREEN(0.41, 0.51721),
	VIOLET(0.4149, 0.1776),
	YELLOW(0.5425, 0.4196),
	BLUE(0.1691, 0.0441);
	
	Double x;
	Double y;
	
	private MoodColor(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public Double getX() {
		return x;
	}
	public Double getY() {
		return y;
	}
}

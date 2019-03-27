package adapt.learn;

import java.util.ArrayList;

public class Topic {
	
	private String topicString;
	private ArrayList<Level> levels;
	
	public Topic(){
	
	}
	public String getTopicString() {
		return topicString;
	}
	public void setTopicString(String topicString) {
		this.topicString = topicString;
	}
	public ArrayList<Level> getLevels() {
		return levels;
	}
	public void setLevels(ArrayList<Level> levels) {
		this.levels = levels;
	}
	
	
	
}

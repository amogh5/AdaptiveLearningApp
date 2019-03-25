package adapt.learn;

import java.util.ArrayList;

public class Topic {
	
	private String topic;
	private ArrayList<Content> contents;
	
	public Topic() {}
	public Topic(String topic, ArrayList<Content> contents) {
		this.topic = topic;
		this.contents = contents;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public ArrayList<Content> getContents() {
		return contents;
	}
	public void setContents(ArrayList<Content> contents) {
		this.contents = contents;
	}
	
}

package adapt.learn;

import java.util.ArrayList;

public class Content {

	private ArrayList<String> tutorial;
	private ArrayList<String> example;
	private ArrayList<String> test;
	private int level;
	
	public Content() {}
	
	public Content(ArrayList<String> tutorial, ArrayList<String> example, ArrayList<String> test, int level) {
		this.tutorial = tutorial;
		this.example = example;
		this.test = test;
		this.level = level;
	}
	
	public ArrayList<String> getTutorial() {
		return tutorial;
	}
	public void setTutorial(ArrayList<String> tutorial) {
		this.tutorial = tutorial;
	}
	public ArrayList<String> getTest() {
		return test;
	}
	public void setTest(ArrayList<String> test) {
		this.test = test;
	}
	public ArrayList<String> getExample() {
		return example;
	}
	public void setExample(ArrayList<String> example) {
		this.example = example;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}

package adapt.learn;

import java.util.ArrayList;

public class Level {
	private int levelNumber;
	private String description;
	private String example;
	private String quiz;
	private ArrayList<Integer> options;
	private int answer;
	
	public Level() {
		
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExample() {
		return example;
	}
	public void setExample(String example) {
		this.example = example;
	}
	public int getLevelNumber() {
		return levelNumber;
	}
	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}
	public ArrayList<Integer> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<Integer> options) {
		this.options = options;
	}
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	

}

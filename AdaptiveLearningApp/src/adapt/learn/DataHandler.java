package adapt.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Servlet implementation class DataHandler
 */
public class DataHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public static void parseToJSON(Topic topic)
	{
		ObjectMapper objectMapper = new ObjectMapper();
//		Topic top
	}
	public static ArrayList<Topic> parseFromJSON()
	{
		
		File file = new File(Constants.contentFile); 

		BufferedReader br=null;
		String line=null; 
		StringBuffer fileContents = new StringBuffer();

		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) 
			{
				fileContents.append(line);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String json = fileContents.toString();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ArrayList<Topic> topics = new ArrayList<>();
		try {
			topics = objectMapper.readValue(json, new TypeReference<ArrayList<Topic>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(json);
		
		System.out.println("Number of topics: " + topics.size());
		System.out.println(topics.get(0).getTopicString());
		System.out.println("SIZE: " + topics.get(0).getLevels().size());
		
		return topics;
	}
	
	public static void sendData(String topicString)
	{
		int userId = 1;
		
		int levelNumber = getUserLevel(userId);
		
		//send tutorial, example, test triplet
		ArrayList<Topic> topics = parseFromJSON();
		Level level = null;
		for(Topic t : topics)
		{
			if(t.getTopicString().equals(topicString) )
				for(Level l : t.getLevels())
					if(l.getLevelNumber() == levelNumber)
						level =l;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String json = objectMapper.writeValueAsString(level);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int getUserLevel(int userId) {
		
		int level = 0;
		File file = new File(Constants.userLevelFile); 

		BufferedReader br=null;
		String line=null; 
		ArrayList<String> userLevels = new ArrayList<>();

		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) 
			{
				userLevels.add(line);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(String s: userLevels)
		{
			if(s.contains(userId + ",LEVEL"))
				level = Integer.parseInt(s.split(",LEVEL")[1]);
		}
		
		return level;
	}
	
	public static void isAnswerCorrect(int answer) {
		
		File file = new File(Constants.lastFiveFile); 
		int negCount = 0, posCount = 0, userId = 1;

		BufferedReader br=null;
		String line=null; 
		Queue<Integer> lastFiveQueue = new LinkedList<>(); 
	
		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) 
			{
				lastFiveQueue.add(Integer.parseInt(line));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		lastFiveQueue.remove();
		lastFiveQueue.add(answer);
		
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(Constants.lastFiveFile));
			for(int b : lastFiveQueue)
			{
					bufferedWriter.write(String.valueOf(b) + "\n");
					if(b == -1)
						negCount++;
					else if(b == 1)
						posCount++;
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int newLevel = 0;
		if(posCount > negCount && getUserLevel(userId) < 5 && getUserLevel(userId) > 0) {
			newLevel = getUserLevel(userId) + 1;
			System.out.println("Increase user level");
		}
		else if(posCount < negCount && getUserLevel(userId) > 1 && getUserLevel(userId) < 6) {
			newLevel = getUserLevel(userId) - 1;
			System.out.println("Decrease user level");
		}
		else
			newLevel = getUserLevel(userId);
		//update user model with new user level
		updateUserLevel(userId, getUserLevel(userId), newLevel);
		
	}
	
	public static void updateUserLevel(int userId, int oldLevel, int newLevel) {
		File file = new File(Constants.userLevelFile); 

		BufferedReader br=null;
		String line=null; 
		ArrayList<String> userLevels = new ArrayList<>();  //1,LEVEL2

		try {

			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) 
			{
				userLevels.add(line);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedWriter bufferedWriter = null;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter(file));
			for(String s : userLevels)
			{
					if(s.contains(userId + ",LEVEL" + oldLevel))
						bufferedWriter.write(userId + ",LEVEL" + newLevel + "\n");
					else
						bufferedWriter.write(s + "\n");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				bufferedWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

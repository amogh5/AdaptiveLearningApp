package adapt.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class InitialAppHandler
 */
public class InitialAppHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor. 
	 */
	public InitialAppHandler() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		String method = null;
		String rating =  null;
		String result = null;
		String answer =null;
		String experience =null;
		String userId =null;

		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		JSONObject jObj;

		try {
			jObj = new JSONObject(sb.toString());
			method = jObj.getString("requestMethod");

			if(method.equalsIgnoreCase(Constants.fetchEnrollQues))
			{
				rating = jObj.getString("rating");				
				result=fetchEnrollmentQuestions(rating);
			}
			else if(method.equalsIgnoreCase(Constants.evaluateEnrollQues))
			{
				rating = jObj.getString("rating");				
				answer = jObj.getString("answer");
				result=evaluateEnrollmentQuestions(rating,answer);
			}
			else if(method.equalsIgnoreCase(Constants.calculateUserLevel))
			{
				experience = jObj.getString("experience");
				rating = jObj.getString("rating");
				userId= jObj.getString("userId");
				userLevelHeuristicFunct(userId,rating, experience);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);	

	}


	public static String fetchEnrollmentQuestions(String rating)
	{
		File file = new File(Constants.enrollQuesFile); 

		BufferedReader br=null;
		String st=null; 
		String[] line = null;
		String question =null;

		try {

			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) 
			{
				line = st.split(",");
				if(line[0].equalsIgnoreCase(rating))
					question= line[1];
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

		return question;
	}

	public static String evaluateEnrollmentQuestions(String rating,String answer)
	{
		File file = new File(Constants.enrollSolnFile); 

		BufferedReader br=null;
		String st=null; 
		String[] line = null;
		String solution =null;

		try {

			br = new BufferedReader(new FileReader(file));
			while ((st = br.readLine()) != null) 
			{
				line = st.split(",");
				if(line[0].equalsIgnoreCase(rating))
					solution= line[1];
				break;
			}
			if(solution.equalsIgnoreCase(answer))
			{
				solution=fetchEnrollmentQuestions(rating+1);
			}
			else
			{
				solution="";
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

		return solution;
	}


	public static void userLevelHeuristicFunct(String userId,String rating,String experience)
	{
		File file = new File(Constants.userLevelFile); 

		BufferedWriter br=null;
		int userLevel=1;
		String userData=null;
		try {
			
			userLevel= ((Integer.parseInt(rating.trim())+Integer.parseInt(experience.trim()))/2);
			userData= (new StringBuffer(userId).append(",").append(Constants.levelString).append(userLevel).append("\n")).toString();
			br = new BufferedWriter(new FileWriter(file));
			br.append(userData);

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
	}
	

}

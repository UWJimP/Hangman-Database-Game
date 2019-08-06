package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A utility class used to get words from the Heroku Database and send post results of the game.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 1, 2019
 */
public class WordGameDatabase {

	/**
	 * The URL of the database.
	 */
	private static final String URL = "https://word-game-database.herokuapp.com/word?id=";
	
	/**
	 * The random class used by the class.
	 */
	private static final Random RANDOM = new Random();
	
	/**
	 * The number of words in the database.
	 */
	private static final int NUM_WORDS = 4287;
	
	/**
	 * The number of errors.
	 */
	private static int NUM_ERROR = 0;
	
	private WordGameDatabase() {
		
	}
	
	/**
	 * Get method used to get a word from the database.
	 * 
	 * @return Returns the JSONObject from the database.
	 */
	public static JSONObject getMethod() {
		Scanner scanner = null;
		JSONObject json = null;
		try {
			//Creates the URL used to access.
			int id = RANDOM.nextInt(NUM_WORDS) + 1;
			URI uri = new URI(URL + id);
			URL connection = uri.toURL();
			//System.out.println(connection.toString());
			
			//Grab the response from the server.
			scanner = new Scanner(connection.openStream());
			String response = scanner.next();

			//Parse the data
			JSONParser parser = new JSONParser();
			JSONObject jsonResponse = (JSONObject) parser.parse(response);
			if(jsonResponse != null && (Boolean) jsonResponse.get("success")) {
				json = (JSONObject) parser.parse(jsonResponse.get("word").toString());
				NUM_ERROR = 0;
			} else {
				//Performs error check if for some reason the data is not obtained.
				//Will keep checking 100 times until something is found.
				System.out.println("Connection Error");
				NUM_ERROR++;
				if(NUM_ERROR > 100) {
					NUM_ERROR = 0;
					return null;
				} else {
					return getMethod();
				}
			}
			scanner.close();
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null) {
				scanner.close();
			}
		}
		return json;
	}
	
	/**
	 * Post method used to send info to the heroku database.
	 * 
	 * @param path The path
	 * @param post The JSON Object to be posted.
	 * @return A JSON Object response.
	 */
	public static JSONObject postMethod(String path, JSONObject post) {
		String response = null;
		JSONObject json = null;
		try {
			URL url = new URL("https://word-game-database.herokuapp.com/" + path);
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(post.toJSONString());
			writer.flush();
			writer.close();
			
			Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
					"UTF-8"));
			StringBuilder sb = new StringBuilder();
			int c;
			while((c = in.read()) >= 0) {
				sb.append((char)c);
			}
			response = sb.toString();

			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(response);
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
}

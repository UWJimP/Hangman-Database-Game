package control;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;

/**
 * Controls the input and output statements to the Postgres SQL database.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Jul 12, 2019
 */
public class DatabaseController {

	private static final String URL = "https://word-game-database.herokuapp.com/word?id=";
	
	private static final Random RANDOM = new Random();
	
	private DatabaseController() {
		
	}
	
	public static String getResult() {
		String result = "";
		//URI uri = new URI().;
		return result;
	}
	
	public static void main(String[] args) {
		try {
			URL url = new URL("https://word-game-database.herokuapp.com/reset");
			//String postData = "p1=500";
			Map<String, String> postData1 = new HashMap<String, String>();
			//JSONObject postData = new JSONObject();
			postData1.put("success", "false");
			postData1.put("id", "510");
			JSONObject postData = new JSONObject(postData1);
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			System.out.println(postData.toString());
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(postData.toJSONString());
			writer.flush();
			writer.close();
			
			Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			int c;
			while((c = in.read()) >= 0) {
				sb.append((char)c);
			}
			String response = sb.toString();
			System.out.println(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void getMethod() {
		HttpURLConnection urlConnection = null;
		try {
			URI uri = new URI(URL + 500);
			URL connection = uri.toURL();
			System.out.println(connection.toString());
			//urlConnection = (HttpURLConnection) connection.openConnection();
			Scanner scanner = new Scanner(connection.openStream());
			String response = scanner.next();
			System.out.println(response);
			scanner.close();
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//urlConnection.disconnect();
		}
	}
	
	private static void postMethod() {
		try {
			URL url = new URL("https://word-game-database.herokuapp.com/test");
			Map<String, String> postData = new HashMap<String, String>();
			postData.put("name", "510");
			JSONObject jsonData = new JSONObject(postData);
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			System.out.println(postData.toString());
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(jsonData.toJSONString());
			writer.flush();
			writer.close();
			
			Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			StringBuilder sb = new StringBuilder();
			int c;
			while((c = in.read()) >= 0) {
				sb.append((char)c);
			}
			String response = sb.toString();
			System.out.println(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

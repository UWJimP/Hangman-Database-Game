package setup;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class that writes a PostgreSQL query for creation of the database values. 
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Jul 20, 2019
 */
public class SQLQueryWriter {

	
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("word_list.txt"));
			String line;
			line = reader.readLine();
			while(line != null) {
				words.add(line);
				line = reader.readLine();
			}
			
			reader.close();
			
			System.out.println("Writing SQL query file");
			
			PrintWriter seedWriter = new PrintWriter("SQLSetUp.sql", "UTF-8");
			
			seedWriter.println("CREATE TABLE words_list(id SERIAL, word TEXT NOT NULL, "
					+ "uniqueChars INTEGER NOT NULL, "
					+ "success INTEGER DEFAULT 0, failure INTEGER DEFAULT 0);");
			
			seedWriter.println("INSERT INTO words_list (word, uniqueChars) VALUES ");
			for(int index = 0; index < words.size(); index++) {
				String theWord = words.get(index);
				Set<Character> letters = new HashSet<Character>();
				for(char letter : theWord.toCharArray()) {
					letters.add(letter);
				}
				
				seedWriter.print("(\'" + words.get(index) + "\', " + letters.size() + ")");
				if(index == words.size() - 1) {
					seedWriter.print(";");
				} else {
					seedWriter.println(", ");
				}
			}
			seedWriter.close();
			System.out.println("Completed writing SQL query file");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

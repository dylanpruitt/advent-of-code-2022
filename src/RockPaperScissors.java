import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RockPaperScissors {
	// where X, Y, Z is playing rock, paper, scissors
	static ArrayList<String> winningCombinations = new ArrayList<>(Arrays.asList("AY","BZ","CX"));
	static ArrayList<String> drawCombinations    = new ArrayList<>(Arrays.asList("AX","BY","CZ"));
	static ArrayList<String> losingCombinations  = new ArrayList<>(Arrays.asList("AZ","BX","CY"));
	
	private static int getScore(char first, char response) {
		// X=88, Y=89, Z=90, so score = response-87
		int score = ((int)response) - 87;
		
		// kind of a hacky way to build this string
		String play = "" + first + response;
		if (winningCombinations.contains(play)) {
			score += 6;
		} else if (drawCombinations.contains(play)) {
			score += 3;
		}
		
		return score;
	}
	
	private static int getScoreV2(char first, char response) {
		int score = 0;
		char shape = ' ';
				
		if (response == 'Y') {
			score += 3;
			String combination = drawCombinations
					.stream().filter(s -> s.contains(""+first)).findFirst().orElse("");
			shape = combination.charAt(1);
		} else if (response == 'Z') {
			score += 6;
			String combination = winningCombinations
					.stream().filter(s -> s.contains(""+first)).findFirst().orElse("");
			shape = combination.charAt(1);
		} else {
			String combination = losingCombinations
					.stream().filter(s -> s.contains(""+first)).findFirst().orElse("");
			shape = combination.charAt(1);
		}
		
		score += ((int)shape) - 87;
		return score;
	}
	
	public static void main(String[] args) {
		try {
			File file = new File("src/input2.txt");
			Scanner reader   = new Scanner(file);
			
			Integer totalScore = 0;
			
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				String[] params = data.split("\s");
				totalScore += getScoreV2(params[0].charAt(0), params[1].charAt(0));
			}
			
			System.out.println(totalScore);
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalorieCount {
	public static void main(String[] args) {
		try {
			File calorieFile = new File("src/input.txt");
			Scanner reader   = new Scanner(calorieFile);
			
			ArrayList<Integer> calorieCounts = new ArrayList<>();
			Integer thisCount = 0;
			
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.isBlank()) {
					calorieCounts.add(thisCount);
					thisCount = 0;
				} else {
					thisCount += Integer.parseInt(data);
				}
			}
			
			// 1st star solution
			System.out.println(calorieCounts.stream().max((a,b) -> a - b));
			
			// 2nd star solution
			System.out.println(calorieCounts.stream().sorted((a,b) -> b - a).limit(3).reduce(Integer::sum));
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}

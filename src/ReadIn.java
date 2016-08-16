
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dataStructures.HashMap;

public class ReadIn {

	private static boolean add;

	public static void main(String[] args) {
		
		String fileName = "C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\movies.dat";
		ArrayList<User> userList = new ArrayList<User>(943);  // row indexes are the users and columns indexes are the movie ratings
		
		
		ArrayList<MovieStructure> movieList = new ArrayList<MovieStructure>();
		ArrayList<RatingStructure> ratingList = new ArrayList<>();
		
		try {
			
			Scanner fileData = new Scanner(new File(fileName));
		
			// iterate through the list
			while (fileData.hasNextLine()) {
				
				String line = fileData.nextLine();  
				String[] tokens = line.split("\\|"); // split string using "pipe" as delimiter 

				
				// iterate over each word array token
				
				MovieStructure movie = new MovieStructure(Integer.parseInt(tokens[0]), tokens[1]);
				movieList.add(movie);

			}
		
			fileData.close(); // close file scanner
		
		
		} catch (FileNotFoundException ex) {
			System.out.println("Scanner error opening the file " + fileName);
			System.out.println(ex.getMessage());
		}
		
		
		
		fileName = "C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\ratings.dat";
		
		
		try {
			
			Scanner fileData = new Scanner(new File(fileName));
		
			// iterate through the list
			while (fileData.hasNextLine()) {
				
				String line = fileData.nextLine();  
				String[] tokens = line.split("\t"); // split string using "pipe" as delimiter 

				// iterate over each word array token
				
				RatingStructure rating = new RatingStructure(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
				ratingList.add(rating);

			}
		
			fileData.close(); // close file scanner
		
		
		} catch (FileNotFoundException ex) {
			System.out.println("Scanner error opening the file " + fileName);
			System.out.println(ex.getMessage());
		}
		
		
		
		//System.out.println(movieList);  // Test whether readIn works for movie, it does!
		//System.out.println(ratingList);  // Test whether readIn works for rating, it does!
		
		for (RatingStructure item : ratingList) {
			
			
			
			//HashMap<Integer, Integer> tempMap = new HashMap<>();
			User user = new User(item.getUserID());
			user.addToHashMap(item.getMovieID(), item.getMovieRating());
			userList.add(user);

		}
		try {
			PrintWriter outputfile = new PrintWriter(new File("C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\output.txt"));
			
			for (User u : userList) {
				outputfile.print(u);
			}
			
			outputfile.close();
			
			
		} catch (Exception e) {
			
		}
		
		
		
		
		
		// to access userList its userID - 1 then access rating movieRating - 1
		
		/*
		for (int userCount = 0; userCount < ratingList.size(); userCount++) {
			Structure entry = new Structure();
			entry.addToMap(userCount, ratingList.get(userCount).getMovieID());
			entry.getUserProvidedRatings().add(ratingList.get(userCount).getMovieRating());
			userList.set(ratingList.get(userCount).getUserID()-1, entry);
		}
			
		for (int row = 0; row < userList.size(); row++) {
			System.out.println(userList.get(row));
		}
		*/
	}
	

}

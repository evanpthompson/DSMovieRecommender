
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dataStructures.HashMap;
import dataStructures.MapIterator;

public class ReadIn {

	public static void main(String[] args) {
		
		String fileName = "C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\movies.dat";
		HashMap<Integer, User> userList = new HashMap<>();  // row indexes are the users and columns indexes are the movie ratings
		
		
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
			
			for (int i = 1; i < 944; i++) {
				
				if (userList.containsKey(item.getUserID())) {
					userList.get(item.getUserID()).addToHashMap(item.getMovieID(), item.getMovieRating());
				}
				else {
					User user = new User(item.getUserID());
					user.addToHashMap(item.getMovieID(), item.getMovieRating());
					userList.add(item.getUserID(), user);
				}
				
				
			}
			
			//HashMap<Integer, Integer> tempMap = new HashMap<>();

		}
		
		// iterate through each user
		for (int userCount = 1; userCount < 944; userCount++) {
			// iterate through each movie
			for (int movieCount = 1; movieCount < 1683; movieCount++) {
				if (!(userList.get(userCount).getUserRatings().containsKey(movieCount))) {
					userList.get(userCount).getUserRatings().add(movieCount, 0);
				}
			}
		}
		
		// iterate through each user to verify the size of each hashmap
		/*for (int userCount = 1; userCount < 944; userCount++) {
			System.out.println("userID: " + userList.get(userCount).getUserRatings().size());
		}*/
		
		
		Double[][] pairwise = new Double[1682][1682];
		
		for (int itemRow = 0; itemRow < pairwise.length; itemRow++) {
			for (int itemColumn = 0; itemColumn < pairwise[itemRow].length; itemColumn++) {
				
				if (itemRow == itemColumn) {
					pairwise[itemRow][itemColumn] = 1.0;
				}
				else if (itemRow > itemColumn) {
					pairwise[itemRow][itemColumn] = pairwise[itemColumn][itemRow];
				}
				else {
					pairwise[itemRow][itemColumn] = calculateSimilarity(userList, itemRow+1, itemColumn+1);
				}
				
				
			}
		}
		
		try {
			
			PrintWriter outputfile = new PrintWriter(new File("C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\pairwiseverify.txt"));
			
			
			for (int i = 0; i < pairwise.length; i++) {
				
				outputfile.print("row: " + Integer.toString(i) + "\t");
				
				for (int j = 0; j < pairwise[i].length; j++) {
					outputfile.print(Double.toString(pairwise[i][j]) + "\t");
				}
				outputfile.println();
			}
			
			
		} catch (Exception e) {
			
		}
		
		
		/* output to text file to verify data */
		/*
		try {
			PrintWriter outputfile = new PrintWriter(new File("C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\output.txt"));
			
			MapIterator<Integer, User> itr = userList.mapIterator();
			
			
			
			while (itr.hasNext()) {
				
				String outString = "userID: " + itr.getCurrentKey() + " ";
				
				outString += " movieID: " + Integer.toString(itr.getCurrentKey()) + userList.get(itr.getCurrentKey()).toString() + '\n';
				outputfile.print(outString);
				
				itr.next();
			
			}
			
			outputfile.close();
			
			
		} catch (Exception e) {
			
		}
		*/
		
		
		
		
		
	}
	
	public static double calculateRating(double u, double i) {
		double outputDouble = 0.0;
		
		
		
		return outputDouble;
	}
	
	
	// Calculate the pairwise similarity between 2 columns
	public static double calculateSimilarity(HashMap<Integer, User> userList, int i, int j) {
		double outputDouble = 0.0;
		
		double sum = 0.0;
		double columnI = 0.0;
		double columnJ = 0.0;
		
		// range of ints for i and j are [1-1682]
		// calc sum of column i and column j for every user
		
		MapIterator<Integer, User> itr = userList.mapIterator();
		
		
		
		while (itr.hasNext()) {
			
			sum += userList.get(itr.getCurrentKey()).getUserRatings().get(i) * userList.get(itr.getCurrentKey()).getUserRatings().get(j);
			columnI += Math.pow(userList.get(itr.getCurrentKey()).getUserRatings().get(i), 2);
			columnJ += Math.pow(userList.get(itr.getCurrentKey()).getUserRatings().get(j), 2);
			itr.next();
		
		}
		
		outputDouble = (sum / (Math.sqrt(columnI) * Math.sqrt(columnJ)));
		
		return outputDouble;
	}
	
	public int calculateUserSum(HashMap<Integer, Integer> userRow) {
		int outputInt =  0;
		
		for (int j = 1; j <= userRow.size(); j ++) {
			outputInt += userRow.get(j);
		}
		
		return outputInt;
	}
	

}


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import dataStructures.HashMap;
import dataStructures.MapIterator;

public class ReadIn {

	public static void main(String[] args) {
		
		int NUMBER_OF_USERS = 943;
		int NUMBER_OF_MOVIES = 1682;
		
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
		for (int userCount = 1; userCount < NUMBER_OF_USERS+1; userCount++) {
			// iterate through each movie
			for (int movieCount = 1; movieCount < NUMBER_OF_MOVIES+1; movieCount++) {
				if (!(userList.get(userCount).getUserRatings().containsKey(movieCount))) {
					userList.get(userCount).getUserRatings().add(movieCount, 0);
				}
			}
		}
		
		// iterate through each user to verify the size of each hashmap
		/*for (int userCount = 1; userCount < 944; userCount++) {
			System.out.println("userID: " + userList.get(userCount).getUserRatings().size());
		}*/
		
		// pairwise is 0 indexed while the movieList is 1 indexed, pay attention to this 
		Double[][] pairwise = new Double[NUMBER_OF_MOVIES][NUMBER_OF_MOVIES];
		
		for (int itemRow = 0; itemRow < pairwise.length; itemRow++) {
			for (int itemColumn = 0; itemColumn < pairwise[itemRow].length; itemColumn++) {
				
				if (itemRow == itemColumn) {
					pairwise[itemRow][itemColumn] = 1.0;
				}
				/*else if (itemRow > itemColumn) {
					pairwise[itemRow][itemColumn] = pairwise[itemColumn][itemRow];
				}*/
				else {
					pairwise[itemRow][itemColumn] = calculateSimilarity(userList, itemRow+1, itemColumn+1);
				}
				
				
			}
			
		}
		
		
		
		// predictions
		for (int userCount = 1; userCount < NUMBER_OF_USERS+1; userCount++) {
			// iterate through each movie
			for (int movieCount = 1; movieCount < NUMBER_OF_MOVIES+1; movieCount++) {
				if (userList.get(userCount).getUserRatings().get(movieCount) == 0) {
					userList.get(userCount).addToHashMap(movieCount, calculateRating(userList.get(userCount).getUserRatings(), pairwise, movieCount));
				}
			}
			
			int[] topFive = getNumberOfMaximum(userList.get(userCount).getPredictedUserRatings());
			
			for (int i = 0; i < topFive.length; i++) {
				System.out.print(" movieID: " + movieList.get(topFive[i]).getMovieName() );
			}
			
			System.out.println();
		
		}
		
		
		/*
		// iterate through each user to verify the size of each predictor hashmap
		for (int userCount = 1; userCount < NUMBER_OF_USERS+1; userCount++) {
			System.out.println("userID: " + Integer.toString(userCount) + " " + userList.get(userCount).getPredictedUserRatings().size());
		}
		*/
		
		
		/*  Output pairwise values to text file  */
		/*
		try {
			
			PrintWriter outputfile = new PrintWriter(new File("C:\\Users\\evanpthompson\\Projects\\DSSemesterProject\\src\\pairwiseverifyformatted.txt"));
			
			
			for (int i = 0; i < pairwise.length; i++) {
				
				outputfile.print("row: " + Integer.toString(i) + "\t");
				
				for (int j = 0; j < pairwise[i].length; j++) {
					outputfile.print(String.format("%1.20f \t", pairwise[i][j]));
				}
				outputfile.println();
			}
			
			
		} catch (Exception e) {
			
		}
		*/
		
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
	
	public static double calculateRating(HashMap<Integer, Integer> userRatings, Double[][] pairwise, int movieNumber) {
		double outputDouble = 0.0;
		double num = 0.0;
		double denom = 0.0;
		
		for (int i = 1; i <= userRatings.size(); i++) {
			num += userRatings.get(i) * pairwise[i-1][movieNumber-1];
			
			if (userRatings.get(i) != 0) {
				denom += pairwise[i-1][movieNumber-1];
			}
		}
		
		outputDouble = num / denom;
		
		
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
	
	public static int[] getNumberOfMaximum(HashMap<Integer, Double> predictedUserRatings) {
		int[] keyArr = new int[5];
		double[] valueArr = new double[5];
		
		/*
		// initialize the values of the value array
		for (int i = 0; i < valueArr.length; i++) {
			valueArr[i] = 0.0;
		}
		*/
		
		MapIterator<Integer, Double> itr = predictedUserRatings.mapIterator();
		
		while (itr.hasNext()) {
			
			if (valueArr[0] < predictedUserRatings.get(itr.getCurrentKey())) {
				valueArr[0] = predictedUserRatings.get(itr.getCurrentKey());
				keyArr[0] = itr.getCurrentKey();
			}
			else if (valueArr[1] < predictedUserRatings.get(itr.getCurrentKey())) {
				valueArr[1] = predictedUserRatings.get(itr.getCurrentKey());
				keyArr[1] = itr.getCurrentKey();
			}
			else if (valueArr[2] < predictedUserRatings.get(itr.getCurrentKey())) {
				valueArr[2] = predictedUserRatings.get(itr.getCurrentKey());
				keyArr[2] = itr.getCurrentKey();
			}
			else if (valueArr[3] < predictedUserRatings.get(itr.getCurrentKey())) {
				valueArr[3] = predictedUserRatings.get(itr.getCurrentKey());
				keyArr[3] = itr.getCurrentKey();
			}
			else if (valueArr[4] < predictedUserRatings.get(itr.getCurrentKey())) {
				valueArr[4] = predictedUserRatings.get(itr.getCurrentKey());
				keyArr[4] = itr.getCurrentKey();
			}
			
			itr.next();
		
		}
		
		return keyArr;
	}

}

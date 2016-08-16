import java.util.ArrayList;

import dataStructures.HashMap;

public class Structure {
	
	private static HashMap<Integer, Integer> map; // field that contains the column index integer. Key is the array index and value is the movie column index
	private ArrayList<Integer> userProvidedRatings;
	
	public Structure() {  }
	
	public Structure(HashMap<Integer, Integer> map, ArrayList<Integer> userProvidedRatings) {
		this.map = map;
		this.userProvidedRatings = userProvidedRatings;
	}

	public static HashMap<Integer, Integer> getMap() {
		return map;
	}
	
	private void setMap(HashMap<Integer, Integer> map) {
		this.map = map;
	}

	public ArrayList<Integer> getUserProvidedRatings() {
		return userProvidedRatings;
	}

	private void setUserProvidedRatings(ArrayList<Integer> userProvidedRatings) {
		this.userProvidedRatings = userProvidedRatings;
	}

	public static void addToMap(int key, int value) {
		map.add(key, value);
	}
	
	
	
	
	

}

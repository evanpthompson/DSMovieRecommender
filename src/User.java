import dataStructures.HashMap;
import dataStructures.MapIterator;

public class User {
	
	private int id;
	private HashMap<Integer, Integer> userRatings;
	private HashMap<Integer, Double> predictedUserRatings;
	
	public User(int id) {
		this.id = id;
		this.userRatings = new HashMap<>();
	}
	

	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}

	public HashMap<Integer, Integer> getUserRatings() {
		return userRatings;
	}

	private void setUserRatings(HashMap<Integer, Integer> userRatings) {
		this.userRatings = userRatings;
	}
	
	public HashMap<Integer, Double> getPredictedUserRatings() {
		return predictedUserRatings;
	}
	
	private void setPredictedUserRatings(HashMap<Integer, Double> predictedUserRatings) {
		this.predictedUserRatings = predictedUserRatings;
	}
	
	
	
	public void addToHashMap(int key, int value) {
		this.userRatings.add(key, value);
	}
	
	

	@Override
	public String toString() {
		
		MapIterator<Integer, Integer> itr = userRatings.mapIterator();
		
		String outString = "userID: " + this.id + '\n';
		
		while (itr.hasNext()) {
			outString += " movieID: " + Integer.toString(itr.getCurrentKey()) + " userRating: " + Integer.toString(userRatings.get(itr.getCurrentKey())) + '\n';
			itr.next();
		
		}
		
		return outString;
	}
	
	
	

}

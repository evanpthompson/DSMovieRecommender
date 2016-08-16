
public class RatingStructure {

	
	private int userID;
	private int movieID;
	private int movieRating;
	
	
	public RatingStructure() {  }
	
	public RatingStructure(int userID, int movieID, int movieRating) {
		this.userID = userID;
		this.movieID = movieID;
		this.movieRating = movieRating;
	}



	public int getUserID() {
		return userID;
	}
	
	private void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMovieID() {
		return movieID;
	}
	
	private void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public int getMovieRating() {
		return movieRating;
	}

	private void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}

	

	@Override
	public String toString() {
		
		String outstring = "userID: " + Integer.toString(this.userID) + " | movieID: " + Integer.toString(this.movieID) + " | movieRating: " + Integer.toString(this.movieRating) + '\n';
		
		return outstring;
	}	
	
	
	
}

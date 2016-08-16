
public class MovieStructure {

	private int movieID;
	private String movieName;
	
	public MovieStructure() {  }
	
	public MovieStructure(int movieID, String movieName) {
		this.movieID = movieID;
		this.movieName = movieName;
	}


	public int getMovieID() {
		return movieID;
	}
	
	private void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieName() {
		return movieName;
	}

	private void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	@Override
	public String toString() {
		
		String outstring = "movieID: " + Integer.toString(this.movieID) + " | movieName: " + this.movieName + '\n';
		
		return outstring;
	}
	
	
	
	
	
}

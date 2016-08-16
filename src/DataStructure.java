// Appropriate data structure for this is a dictionary. It will have key, value pairs. Nicely converts to json
	
	 /* { id : 
	  * 	[ "MovieTitle1" : predictedRating, 
	  * 	  "MovieTitle2" : predictedRating, 
	  * 	  "MovieTitle3" : predictedRating, 
	  * 	  "MovieTitle4" : predictedRating, 
	  * 	  "MovieTitle5" : predictedRating
	  * 	]
	  * }
	 * 
	 */


public class DataStructure {

	private int id;
	private KeyValuePair[] kvPair;
	
	
	public DataStructure(int id, KeyValuePair[] kvPair) {
		this.id = id;
		this.kvPair = kvPair;
	}

	
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}

	public KeyValuePair[] getKvPair() {
		return kvPair;
	}

	private void setKvPair(KeyValuePair[] kvPair) {
		this.kvPair = kvPair;
	}


	@Override
	public String toString() {
		
		// Build Key Value string for each entry in list
		String kvString = "";
		
		for (KeyValuePair kv : kvPair) {
			kvString += kv.toString() + "| ";
		}
		
		String outString = "User ID: " + Integer.toString(this.id) + "  Top 5 Recommendations: " + kvString;
		
		return outString;
	}
	
	
	
	
	
	
}

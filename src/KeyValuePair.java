
public class KeyValuePair {

	private String key;
	private double value;
	
	public KeyValuePair(String key, double value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	private void setKey(String key) {
		this.key = key;
	}

	public double getValue() {
		return value;
	}

	private void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		
		String outString = this.key + "::" + Double.toString(this.value);
		
		
		return outString;
	}
	
	
	
	
}

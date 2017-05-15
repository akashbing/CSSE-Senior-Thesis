package generate;

public class BillboardContainer {
	
	// Segments provided by Chicago Data
	private int segment;
	// Picking various themes
	private String advertisementTheme;
	// 1 - 10; 1 - least successful, 10 - most successful
	private int success;
	
	
	public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public String getAdvertisementTheme() {
		return advertisementTheme;
	}
	
	public void setAdvertisementTheme(String advertisementTheme) {
		this.advertisementTheme = advertisementTheme;
	}


	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	@Override
	public String toString() {
		String finalString;
		finalString = this.segment + ", ";
		finalString = finalString + this.advertisementTheme + ", " + this.success;
		return finalString;
	}
}

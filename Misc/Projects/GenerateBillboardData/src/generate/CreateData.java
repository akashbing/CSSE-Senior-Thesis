package generate;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateData {
	
	private static List<BillboardContainer> billboardLocations;
	
	
	public static final String[] ADVERTISEMENT_THEMES = {
		"Automotive", "Clothing", "Educational", "Cellular Service", 
		"Technology", "Medical", "Job", "Recruitment", "Housing", "Religious", 
		"Travel", "Political", "Theater", "Restaurant", "Grocery", "Insurance", 
		"Mobile Devices", "Toiletries", "Lawyer", "Movie", "Television"};

	public static void main(String[] args) {
		billboardLocations = new ArrayList<>();
		for(int i = 1; i < 1310; i++) {
			for (int j = 0; j < 10; j++) { 
				Random rand = new Random();
				BillboardContainer bb = new BillboardContainer();
				bb.setSegment(i);
				int advPickNum = rand.nextInt(ADVERTISEMENT_THEMES.length);
				bb.setAdvertisementTheme(ADVERTISEMENT_THEMES[advPickNum]);
				int successNum = rand.nextInt(10)+1;
				bb.setSuccess(successNum);
				billboardLocations.add(bb);
			}
		}
		
		try {
			PrintWriter writer = new PrintWriter("bill-board-data.csv", "UTF-8");
			for (int i = 0; i < billboardLocations.size(); i++) {
				writer.println(billboardLocations.get(i).toString());
				// System.out.println(billboardLocations.get(i).toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

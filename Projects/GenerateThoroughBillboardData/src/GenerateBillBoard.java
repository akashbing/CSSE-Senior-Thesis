import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class GenerateBillBoard {

	public static final String[] ADVERTISEMENT_THEMES = { "Automotive",
			"Clothing", "Educational", "Cellular Service", "Technology",
			"Medical", "Job", "Recruitment", "Housing", "Religious", "Travel",
			"Political", "Theater", "Restaurant", "Grocery", "Insurance",
			"Mobile Devices", "Toiletries", "Lawyer", "Movie", "Television" };

	public static final String[] REGION_NAMES = { "Edge Water-Uptown",
			"Dunning-Portage-Belmont Cragn",
			"Fuller-Grand Blvd-Washington Park", "Near South-Douglas",
			"South West Side", "South Deering-East Side",
			"Humboldt-Garfield Prk E/W", "Lincoln Park-Lake View",
			"Washington Hts-Roseland-Pullman", "New City-Englewood-W Englewood",
			"Near North", "Rogers Park - West Ridge",
			"Bridgeport-McKinley-Lower West", "West Town-Near West", "Austin",
			"Auburn Gresham-Chatham", "North Park-Albany-Linconl Sq",
			"Irving Park-Avondale-North Ctr", "Riverdale-Hegewisch",
			"Hyde Park-Kenwood-Woodlawn", "Beverly-Mt Greenwood-Morgan Park",
			"Far North West", "South Shore-S Chicago-Avlon",
			"Midway-Garfield Rdg-Clearing", "Lawndale N/S",
			"Hermosa-Logan Square", "Downtown Lakefront", "Ashburn",
			"Chicago Loop" };

	public static List<SegmentAds> segmentAds;
	public static List<RegionAds> regionAds;

	public static void main(String[] args) {
		// Files started at 9/18/2016
		// File ended at 2/23/2017
		segmentAds = new ArrayList<>();
		generateSegmentAdvertisement();
		regionAds = new ArrayList<>();
		generateRegionAdvertisement();
	}

	private static void generateSegmentAdvertisement() {
		for (int i = 1; i < 1310; i++) {
			Calendar c = Calendar.getInstance();
			c.set(2016, 8, 18);
			Calendar cFinal = Calendar.getInstance();
			cFinal.set(2017, 1, 24);
			while (c.getTime().before(cFinal.getTime())) {
				Random r = new Random();
				int advPickNum = r.nextInt(ADVERTISEMENT_THEMES.length);
				int successNum = r.nextInt(10) + 1;
				int length = r.nextInt(30) + 1;
				for (int d = 0; d < length
						&& c.getTime().before(cFinal.getTime()); d++) {
					SegmentAds current = new SegmentAds(i, c.getTime(),
							ADVERTISEMENT_THEMES[advPickNum], successNum);
					segmentAds.add(current);
					c.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter("segmentAds.csv", "UTF-8");
			for (int i = 0; i < segmentAds.size(); i++) {
				writer.println(segmentAds.get(i).toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void generateRegionAdvertisement() {
		for (int i = 0; i < REGION_NAMES.length; i++) {
			Calendar c = Calendar.getInstance();
			c.set(2016, 8, 18);
			Calendar cFinal = Calendar.getInstance();
			cFinal.set(2017, 1, 24);
			while (c.getTime().before(cFinal.getTime())) {
				Random r = new Random();
				int advPickNum = r.nextInt(ADVERTISEMENT_THEMES.length);
				int successNum = r.nextInt(10) + 1;
				int length = r.nextInt(30) + 1;
				for (int d = 0; d < length
						&& c.getTime().before(cFinal.getTime()); d++) {
					RegionAds current = new RegionAds(REGION_NAMES[i], c.getTime(),
							ADVERTISEMENT_THEMES[advPickNum], successNum);
					regionAds.add(current);
					c.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		try {
			PrintWriter writer = new PrintWriter("regionAds.csv", "UTF-8");
			for (int i = 0; i < regionAds.size(); i++) {
				writer.println(regionAds.get(i).toString());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

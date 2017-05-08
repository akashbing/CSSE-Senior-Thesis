import java.util.Calendar;
import java.util.Date;

public class RegionAds {

	private String regionId;
	private Date date;
	private String advertisementTheme;
	private int rating;

	public RegionAds(String ri, Date d, String adTheme, int r) {
		this.regionId = ri;
		this.date = d;
		this.advertisementTheme = adTheme;
		this.rating = r;
	}

	@Override
	public String toString() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH) + 1;
		String d = year + "-" + day + "-" + month;
		return regionId + "," + d + "," + advertisementTheme + "," + rating;
	}

}

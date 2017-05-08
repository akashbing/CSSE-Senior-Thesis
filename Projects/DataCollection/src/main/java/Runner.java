import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Runner {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		firstValues();
		secondValues();
	}

	private static void secondValues() {
		String[] urls = {
				"https://www.zip-codes.com/zip-code/60053/zip-code-60053-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60068/zip-code-60068-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60077/zip-code-60077-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60160/zip-code-60160-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60171/zip-code-60171-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60202/zip-code-60202-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60453/zip-code-60453-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60456/zip-code-60456-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60601/zip-code-60601-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60602/zip-code-60602-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60603/zip-code-60603-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60604/zip-code-60604-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60605/zip-code-60605-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60606/zip-code-60606-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60607/zip-code-60607-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60608/zip-code-60608-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60609/zip-code-60609-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60610/zip-code-60610-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60611/zip-code-60611-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60612/zip-code-60612-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60613/zip-code-60613-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60614/zip-code-60614-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60615/zip-code-60615-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60616/zip-code-60616-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60617/zip-code-60617-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60618/zip-code-60618-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60619/zip-code-60619-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60620/zip-code-60620-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60621/zip-code-60621-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60622/zip-code-60622-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60623/zip-code-60623-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60624/zip-code-60624-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60625/zip-code-60625-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60626/zip-code-60626-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60628/zip-code-60628-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60628/zip-code-60628-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60629/zip-code-60629-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60630/zip-code-60630-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60631/zip-code-60631-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60632/zip-code-60632-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60633/zip-code-60633-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60634/zip-code-60634-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60636/zip-code-60636-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60637/zip-code-60637-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60638/zip-code-60638-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60639/zip-code-60639-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60640/zip-code-60640-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60641/zip-code-60641-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60642/zip-code-60642-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60643/zip-code-60643-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60644/zip-code-60644-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60645/zip-code-60645-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60646/zip-code-60646-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60647/zip-code-60647-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60649/zip-code-60649-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60651/zip-code-60651-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60652/zip-code-60652-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60653/zip-code-60653-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60654/zip-code-60654-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60655/zip-code-60655-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60656/zip-code-60656-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60657/zip-code-60657-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60659/zip-code-60659-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60660/zip-code-60660-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60661/zip-code-60661-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60706/zip-code-60706-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60707/zip-code-60707-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60712/zip-code-60712-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60714/zip-code-60714-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60803/zip-code-60803-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60804/zip-code-60804-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60805/zip-code-60805-2010-census.asp",
				"https://www.zip-codes.com/zip-code/60827/zip-code-60827-2010-census.asp" };

		for (int i = 0; i < urls.length; i++) {
			try {
				Demographic d = new Demographic();
				Document doc = Jsoup.connect(urls[i]).get();
				Elements tables = doc.getElementsByTag("table");
				for (Element e : tables) {
					if (e.className().equals("statTable")) {
						Elements tr = e.getElementsByTag("tr");
						for (Element row : tr) {
							if (row.child(0).text().equals("Under 5 years")
									&& d.getCp().equals("X")) {
								d.setCp(row.child(1).text());
							}
							if (row.child(0).text().equals("5 to 9 years")
									&& d.getP2010().equals("X")) {
								d.setP2010(row.child(1).text());
							}
							if (row.child(0).text().equals("10 to 14 years")
									&& d.getHhpzc().equals("X")) {
								d.setHhpzc(row.child(1).text());
							}
							if (d.getAhv().equals("X") && row.child(0).text()
									.equals("15 to 19 years")) {
								d.setAhv(row.child(1).text());
							}
							if (d.getAiph().equals("X") && row.child(0).text()
									.equals("20 to 24 years")) {
								d.setAiph(row.child(1).text());
							}
							if (d.getPph().equals("X") && row.child(0).text()
									.equals("25 to 29 years")) {
								d.setPph(row.child(1).text());
							}
							if (d.getWp().equals("X") && row.child(0).text()
									.equals("30 to 34 years")) {
								d.setWp(row.child(1).text());
							}
							if (d.getBp().equals("X") && row.child(0).text()
									.equals("35 to 39 years")) {
								d.setBp(row.child(1).text());
							}
							if (d.getHisp().equals("X") && row.child(0).text()
									.equals("40 to 44 years")) {
								d.setHisp(row.child(1).text());
							}
							if (d.getAp().equals("X") && row.child(0).text()
									.equals("45 to 49 years")) {
								d.setAp(row.child(1).text());
							}
							if (d.getAip().equals("X") && row.child(0).text()
									.equals("50 to 54 years")) {
								d.setAip(row.child(1).text());
							}
							if (d.getHawp().equals("X") && row.child(0).text()
									.equals("55 to 59 years")) {
								d.setHawp(row.child(1).text());
							}
							if (d.getOp().equals("X") && row.child(0).text()
									.equals("60 to 64 years")) {
								d.setOp(row.child(1).text());
							}
							if (d.getMp().equals("X") && row.child(0).text()
									.equals("65 to 69 years")) {
								d.setMp(row.child(1).text());
							}
							if (d.getFp().equals("X") && row.child(0).text()
									.equals("70 to 74 years")) {
								d.setFp(row.child(1).text());
							}
							if (d.getMa().equals("X") && row.child(0).text()
									.equals("75 to 79 years")) {
								d.setMa(row.child(1).text());
							}
							if (d.getMma().equals("X") && row.child(0).text()
									.equals("80 to 84 years")) {
								d.setMma(row.child(1).text());
							}
							if (d.getFma().equals("X") && row.child(0).text()
									.equals("85 years and over")) {
								d.setFma(row.child(1).text());
							}

						}
					}
				}
				System.out.println(d.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private static void firstValues() {
		String[] urls = {
				"https://www.zip-codes.com/zip-code/60053/zip-code-60053.asp",
				"https://www.zip-codes.com/zip-code/60068/zip-code-60068.asp",
				"https://www.zip-codes.com/zip-code/60077/zip-code-60077.asp",
				"https://www.zip-codes.com/zip-code/60160/zip-code-60160.asp",
				"https://www.zip-codes.com/zip-code/60171/zip-code-60171.asp",
				"https://www.zip-codes.com/zip-code/60202/zip-code-60202.asp",
				"https://www.zip-codes.com/zip-code/60453/zip-code-60453.asp",
				"https://www.zip-codes.com/zip-code/60456/zip-code-60456.asp",
				"https://www.zip-codes.com/zip-code/60601/zip-code-60601.asp",
				"https://www.zip-codes.com/zip-code/60602/zip-code-60602.asp",
				"https://www.zip-codes.com/zip-code/60603/zip-code-60603.asp",
				"https://www.zip-codes.com/zip-code/60604/zip-code-60604.asp",
				"https://www.zip-codes.com/zip-code/60605/zip-code-60605.asp",
				"https://www.zip-codes.com/zip-code/60606/zip-code-60606.asp",
				"https://www.zip-codes.com/zip-code/60607/zip-code-60607.asp",
				"https://www.zip-codes.com/zip-code/60608/zip-code-60608.asp",
				"https://www.zip-codes.com/zip-code/60609/zip-code-60609.asp",
				"https://www.zip-codes.com/zip-code/60610/zip-code-60610.asp",
				"https://www.zip-codes.com/zip-code/60611/zip-code-60611.asp",
				"https://www.zip-codes.com/zip-code/60612/zip-code-60612.asp",
				"https://www.zip-codes.com/zip-code/60613/zip-code-60613.asp",
				"https://www.zip-codes.com/zip-code/60614/zip-code-60614.asp",
				"https://www.zip-codes.com/zip-code/60615/zip-code-60615.asp",
				"https://www.zip-codes.com/zip-code/60616/zip-code-60616.asp",
				"https://www.zip-codes.com/zip-code/60617/zip-code-60617.asp",
				"https://www.zip-codes.com/zip-code/60618/zip-code-60618.asp",
				"https://www.zip-codes.com/zip-code/60619/zip-code-60619.asp",
				"https://www.zip-codes.com/zip-code/60620/zip-code-60620.asp",
				"https://www.zip-codes.com/zip-code/60621/zip-code-60621.asp",
				"https://www.zip-codes.com/zip-code/60622/zip-code-60622.asp",
				"https://www.zip-codes.com/zip-code/60623/zip-code-60623.asp",
				"https://www.zip-codes.com/zip-code/60624/zip-code-60624.asp",
				"https://www.zip-codes.com/zip-code/60625/zip-code-60625.asp",
				"https://www.zip-codes.com/zip-code/60626/zip-code-60626.asp",
				"https://www.zip-codes.com/zip-code/60628/zip-code-60628.asp",
				"https://www.zip-codes.com/zip-code/60628/zip-code-60628.asp",
				"https://www.zip-codes.com/zip-code/60629/zip-code-60629.asp",
				"https://www.zip-codes.com/zip-code/60630/zip-code-60630.asp",
				"https://www.zip-codes.com/zip-code/60631/zip-code-60631.asp",
				"https://www.zip-codes.com/zip-code/60632/zip-code-60632.asp",
				"https://www.zip-codes.com/zip-code/60633/zip-code-60633.asp",
				"https://www.zip-codes.com/zip-code/60634/zip-code-60634.asp",
				"https://www.zip-codes.com/zip-code/60636/zip-code-60636.asp",
				"https://www.zip-codes.com/zip-code/60637/zip-code-60637.asp",
				"https://www.zip-codes.com/zip-code/60638/zip-code-60638.asp",
				"https://www.zip-codes.com/zip-code/60639/zip-code-60639.asp",
				"https://www.zip-codes.com/zip-code/60640/zip-code-60640.asp",
				"https://www.zip-codes.com/zip-code/60641/zip-code-60641.asp",
				"https://www.zip-codes.com/zip-code/60642/zip-code-60642.asp",
				"https://www.zip-codes.com/zip-code/60643/zip-code-60643.asp",
				"https://www.zip-codes.com/zip-code/60644/zip-code-60644.asp",
				"https://www.zip-codes.com/zip-code/60645/zip-code-60645.asp",
				"https://www.zip-codes.com/zip-code/60646/zip-code-60646.asp",
				"https://www.zip-codes.com/zip-code/60647/zip-code-60647.asp",
				"https://www.zip-codes.com/zip-code/60649/zip-code-60649.asp",
				"https://www.zip-codes.com/zip-code/60651/zip-code-60651.asp",
				"https://www.zip-codes.com/zip-code/60652/zip-code-60652.asp",
				"https://www.zip-codes.com/zip-code/60653/zip-code-60653.asp",
				"https://www.zip-codes.com/zip-code/60654/zip-code-60654.asp",
				"https://www.zip-codes.com/zip-code/60655/zip-code-60655.asp",
				"https://www.zip-codes.com/zip-code/60656/zip-code-60656.asp",
				"https://www.zip-codes.com/zip-code/60657/zip-code-60657.asp",
				"https://www.zip-codes.com/zip-code/60659/zip-code-60659.asp",
				"https://www.zip-codes.com/zip-code/60660/zip-code-60660.asp",
				"https://www.zip-codes.com/zip-code/60661/zip-code-60661.asp",
				"https://www.zip-codes.com/zip-code/60706/zip-code-60706.asp",
				"https://www.zip-codes.com/zip-code/60707/zip-code-60707.asp",
				"https://www.zip-codes.com/zip-code/60712/zip-code-60712.asp",
				"https://www.zip-codes.com/zip-code/60714/zip-code-60714.asp",
				"https://www.zip-codes.com/zip-code/60803/zip-code-60803.asp",
				"https://www.zip-codes.com/zip-code/60804/zip-code-60804.asp",
				"https://www.zip-codes.com/zip-code/60805/zip-code-60805.asp",
				"https://www.zip-codes.com/zip-code/60827/zip-code-60827.asp" };
		for (int i = 0; i < urls.length; i++) {
			try {
				Demographic d = new Demographic();
				Document doc = Jsoup.connect(urls[i]).get();
				Elements tables = doc.getElementsByTag("table");
				for (Element e : tables) {
					if (e.className().equals("statTable")) {
						Elements tr = e.getElementsByTag("tr");
						for (Element row : tr) {
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Current Population:")) {
								d.setCp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("2010 Population:")) {
								d.setP2010(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text().equals(
											"Households per ZIP Code:")) {
								d.setHhpzc(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Average House Value:")) {
								d.setAhv(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text().equals(
											"Avg. Income Per Household:")) {
								d.setAiph(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Persons Per Household:")) {
								d.setPph(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("White Population:")) {
								d.setWp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Black Population:")) {
								d.setBp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Hispanic Population:")) {
								d.setHisp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Asian Population:")) {
								d.setAp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text().equals(
											"American Indian Population:")) {
								d.setAip(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Hawaiian Population:")) {
								d.setHawp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Other Population:")) {
								d.setOp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Male Population:")) {
								d.setMp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Female Population:")) {
								d.setFp(row.child(1).text());
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Median Age:")) {
								d.setMa(row.child(1).text().replaceAll(" years",
										""));
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Male Median Age:")) {
								d.setMma(row.child(1).text()
										.replaceAll(" years", ""));
							}
							if (row.children().size() != 0
									&& row.child(0).children().size() != 0
									&& row.child(0).child(0).text()
											.equals("Female Median Age:")) {
								d.setFma(row.child(1).text()
										.replaceAll(" years", ""));
							}

						}
					}
				}
				System.out.println(d.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}

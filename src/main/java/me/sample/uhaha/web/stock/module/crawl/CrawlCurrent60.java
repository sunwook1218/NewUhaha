package me.sample.uhaha.web.stock.module.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class CrawlCurrent60 {

	private static String BASEURL = "https://finance.naver.com/sise/sise_index_day.nhn";
	private static String KOSPI = "KOSPI";
	private static String KOSDAQ = "KOSDAQ";

	public List<String> getKospi60() throws IOException {
		return getData(KOSPI);
	}

	public List<String> getKosdaq60() throws IOException {
		return getData(KOSDAQ);
	}

	private List<String> getData(String flag) throws IOException {

		List<String> current60 = new ArrayList<>();

		for (int page = 1; page <= 10; page++) {

			int idx = 0;
			
			String url = BASEURL + "?code=" + flag + "&page=" + page;
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("tbody tr");

			for (Element element : elements) {
				if (idx == 2 || idx == 3 || idx == 4 || idx == 9 || idx == 10 || idx == 11) {

					String target = removeComma(element.getElementsByClass("number_1").text());
					String price = target.substring(0, target.indexOf("."));
					current60.add(price);
				}
				idx++;
			}
		}

		return current60;
	}

	private String removeComma(String str) {
		return str.replaceAll("\\,", "");
	}

}

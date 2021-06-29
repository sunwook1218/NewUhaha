package me.sample.uhaha.web.stock.module.crawl;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class TestCrawl {

	public List<String> dataList(int page) {

		String url = "https://finance.naver.com/sise/sise_market_sum.nhn" + "?&page=" + page;

		List<String> dataList = null;

		try {
			Document document = Jsoup.connect(url).get();
			System.out.println("data : " + document);

			Elements select = document.select(".type_2 .number");

			for (Element element : select) {
				
//				dataList.add(element.text());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return dataList;

	}

}

package me.sample.uhaha.web.stock.module;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.sample.uhaha.web.stock.module.crawl.CrawlCurrent60;
import me.sample.uhaha.web.stock.module.crawl.CrawlFundamentalData;
import me.sample.uhaha.web.stock.module.vo.Sector;

@SpringBootTest
public class ScrapTest {
	
	@Autowired
	private CrawlFundamentalData cFunda;
	
	
	void kospi60Test() throws IOException {

		CrawlCurrent60 current60 = new CrawlCurrent60();
		List<String> kospiData = current60.getKospi60();
		List<String> kosdaqData = current60.getKosdaq60();

		if (kospiData == null || kosdaqData == null) {
			System.out.println("data is null");
			return;
		}

		for (String d : kospiData) {
			System.out.println(d);
		}

		for (String d : kosdaqData) {
			System.out.println(d);
		}

	}
	
	@Test
	void fundamentalTest() throws Exception {
		
		
		
		
	}

}

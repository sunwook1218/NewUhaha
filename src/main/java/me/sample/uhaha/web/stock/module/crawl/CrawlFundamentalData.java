package me.sample.uhaha.web.stock.module.crawl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import me.sample.uhaha.web.stock.module.vo.FundamentalData;
import me.sample.uhaha.web.stock.module.vo.Sector;

@Component
public class CrawlFundamentalData {

	private static String BASEURL = "https://finance.naver.com";
	private static String BASEURL_UPJONG = "https://finance.naver.com/sise/sise_group.nhn?type=upjong";

	public Sector setFundamentalDataList(Sector sector) throws IOException {

			List<FundamentalData> fundamentalDataList = new ArrayList<FundamentalData>();

			Document stockListPage = Jsoup.connect(BASEURL + sector.getSectorUrl()).get();
			Elements stocks = stockListPage.select("tbody .name a");
			for (Element s : stocks) {

				FundamentalData fundamentalData = new FundamentalData();

				Document stockDetailPage = Jsoup.connect(BASEURL + s.attr("href")).get();
				String code = stockDetailPage.select(".description .code").text();
				String name = stockDetailPage.select(".h_company .wrap_company a").text();
				fundamentalData.setCode(code);
				fundamentalData.setName(name);
				Elements cellStrong = stockDetailPage.select(".cell_strong");

				System.out.println(name);

				for (int i = 0; i < cellStrong.size(); i++) {
					if (i % 2 == 0) {
						String data = cellStrong.get(i).text().trim();
						switch (i) {
						case 4:
							fundamentalData.setSales(data);
							break;
						case 6:
							fundamentalData.setOperatingProfit(data);
							break;
						case 8:
							fundamentalData.setNetProfit(data);
							break;
						case 10:
							fundamentalData.setOperatingMargin(data);
							break;
						case 12:
							fundamentalData.setNetProfitMargin(data);
							break;
						case 20:
							fundamentalData.setRoe(data);
							break;
						case 22:
							fundamentalData.setPer(data);
							break;
						case 24:
							fundamentalData.setPbr(data);
						}
					}
				}
				System.out.println(fundamentalData.toString());
				fundamentalDataList.add(fundamentalData);
			}
			sector.setFundamentalDataList(fundamentalDataList);

		return sector;

	}

	public List<Sector> getSectorList() throws IOException {

		List<Sector> sectorList = new ArrayList<>();

		Document document = Jsoup.connect(BASEURL_UPJONG).get();
		Elements elements = document.select(".type_1 tbody a");
		for (Element e : elements) {
			String sectorName = e.text();
			String sectorUrl = e.attr("href");
			Sector sector = Sector.builder().sectorName(sectorName).sectorUrl(sectorUrl).build();
			sectorList.add(sector);
		}

		return sectorList;
	}

}

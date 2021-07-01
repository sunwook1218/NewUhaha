package me.sample.uhaha.web.stock;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.uhaha.mybatis.TestMapper;
import me.sample.uhaha.web.stock.module.crawl.CrawlCurrent60;
import me.sample.uhaha.web.stock.module.crawl.CrawlFundamentalData;
import me.sample.uhaha.web.stock.module.crawl.TestCrawl;
import me.sample.uhaha.web.stock.module.excel.ExcelGenerator;
import me.sample.uhaha.web.stock.module.vo.CurrentBundle;
import me.sample.uhaha.web.stock.module.vo.Sector;

@Service
public class StockService {
	
	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private TestCrawl testCrawl;
	
	@Autowired
	private CrawlCurrent60 cCurrent60;
	
	@Autowired
	private CrawlFundamentalData cFunda;
	
	@Autowired
	private ExcelGenerator eg;
	
	public List<MytableVo> getAllMytables() {
		
		return testMapper.selectAllMytable();
	};
	
	public int insertMytable(MytableVo mytableVo) {
		return testMapper.insertMytable(mytableVo);
	}
	
	public List<String> crawlTest(int page) {
		return testCrawl.dataList(page);
	}
	
	public CurrentBundle getCurrentBundle() throws IOException {
		
		return CurrentBundle.builder()
				.kospi60(cCurrent60.getKospi60())
				.kosdaq60(cCurrent60.getKosdaq60())
				.build(); 
	}

	public void doExcelDownload(HttpServletResponse response) throws Exception {
		
//		List<Sector> sectorList = cFunda.getSectorList();
//		for
//		cFunda.setFundamentalDataList(sectorList);
		
		eg.genWorkbook(response);
		
	}

}

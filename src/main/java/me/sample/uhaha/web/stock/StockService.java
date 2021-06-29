package me.sample.uhaha.web.stock;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.uhaha.mybatis.TestMapper;
import me.sample.uhaha.web.stock.module.crawl.CrawlCurrent60;
import me.sample.uhaha.web.stock.module.crawl.TestCrawl;
import me.sample.uhaha.web.stock.module.vo.CurrentBundle;

@Service
public class StockService {
	
	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private TestCrawl testCrawl;
	
	@Autowired
	private CrawlCurrent60 current60;
	
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
				.kospi60(current60.getKospi60())
				.kosdaq60(current60.getKosdaq60())
				.build(); 
	}

}

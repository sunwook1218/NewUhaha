package me.sample.uhaha.web.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.sample.uhaha.mybatis.TestMapper;
import me.sample.uhaha.web.stock.module.crawl.TestCrawl;

@Service
public class StockService {
	
	@Autowired
	private TestMapper testMapper;
	
	@Autowired
	private TestCrawl testCrawl;
	
	public List<MytableVo> getAllMytables() {
		
		return testMapper.selectAllMytable();
	};
	
	public int insertMytable(MytableVo mytableVo) {
		return testMapper.insertMytable(mytableVo);
	}
	
	public List<String> crawlTest(int page) {
		return testCrawl.dataList(page);
	}

}

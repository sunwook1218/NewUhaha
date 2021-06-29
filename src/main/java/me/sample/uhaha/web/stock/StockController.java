package me.sample.uhaha.web.stock;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StockController {
	
	private static Logger logger;

	@Autowired
	private StockService stockService;
	
	@Value("${custom.val}")
	private String custom;

	@GetMapping("/test")
	public String test(Model model) {

		List<MytableVo> allMytables = stockService.getAllMytables();

		model.addAttribute("allData", allMytables);
		model.addAttribute("customData", custom);

		return "test";
	}
	
	@GetMapping("/crawl")
	public String crawl(Model model, Integer page) {
		
		/* Validataion */
		if(page == null) page = 1;
		if(page <= 0) page = 1;
		
		List<String> crawlTest = stockService.crawlTest(page);
		
		model.addAttribute("dataList", crawlTest);
		
		return "crawl";
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {

		
		return "stock/home";
	}
	
	@GetMapping("/login")
	public String login(HttpSession session) {
		
		/* validation */
		Object logMem = session.getAttribute("logMem");
		
		if(logMem != null) {
			session.removeAttribute("logMem");
		}
		
		session.setAttribute("logMem", "newLogMem");
		
		return "redirect:stock/home";
	}

}

package me.sample.uhaha.web.stock;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import me.sample.uhaha.web.stock.module.vo.CurrentBundle;

@RestController
@RequestMapping("/stock")
public class StockRestController {

	@Autowired
	private StockService stockService;

	@PostMapping("/testPost")
	public ResponseMessage post(Model model, MytableVo mytableVo) throws JsonProcessingException {

		int result = stockService.insertMytable(mytableVo);

		ResponseMessage rm = null;
		
		if (result > 0) {
			rm = ResponseMessage.builder().message("s").build();
		} else {
			rm = ResponseMessage.builder().message("f").build();
		}
		
		return rm;
	}
	
	@PostMapping("/currentBundle")
	public CurrentBundle getCurrentBundle() throws IOException {
		return stockService.getCurrentBundle();
	}

}

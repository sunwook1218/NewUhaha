package me.sample.uhaha.web.comic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comic")
public class ComicController {

	@Autowired
	private ComicService comicService;

	@GetMapping("/home")
	public String home(Model model) {

		return "comic/home";
	}

}

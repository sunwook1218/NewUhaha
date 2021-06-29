package me.sample.uhaha.web.fileupload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {
	
	@GetMapping("/home")
	public String home() {
		return "fileUpload/home";
	}

}

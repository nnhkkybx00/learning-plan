package com.example.cookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Controller
public class CookieApplication {


	@RequestMapping("/test")
	public String test(ModelMap map){
		map.addAttribute("name", "sh");
		return "index1";

	}

	public static void main(String[] args) {
		SpringApplication.run(CookieApplication.class, args);
	}
}

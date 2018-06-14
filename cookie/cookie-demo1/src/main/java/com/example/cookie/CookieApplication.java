package com.example.cookie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CookieApplication {


	@RequestMapping("/test")
	public String test(ModelMap map){
// 设置属性
		map.addAttribute("name", "sdfsd");
		// testThymeleaf：为模板文件的名称
		// 对应src/main/resources/templates/testThymeleaf.html
		return "testThymeleaf";

	}

	public static void main(String[] args) {
		SpringApplication.run(CookieApplication.class, args);
	}
}

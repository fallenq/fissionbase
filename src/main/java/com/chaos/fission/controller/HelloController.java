package com.chaos.fission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@RequestMapping("/")
	public String index(@RequestParam(value = "name", defaultValue = "world") String userName, Model model) {
		model.addAttribute("message", "Hello, " + userName);
		return "resultPage";
	}
	
//	@RequestMapping(value="/", method=RequestMethod.POST)
//	public String postIndex(@RequestParam(value = "name", defaultValue = "world") String userName, Model model) {
//		model.addAttribute("message", "Hello, " + userName);
//		return "resultPage";
//	}

}

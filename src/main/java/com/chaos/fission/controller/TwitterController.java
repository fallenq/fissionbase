package com.chaos.fission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/twitter")
public class TwitterController {

	@RequestMapping("/search")
	public String search() {
		return "searchPage";
	}
	
	@RequestMapping("/result")
	public String result(@RequestParam(defaultValue="spirngMvc4") String search, Model model) {
		model.addAttribute("message", "Hello, " + search);
		return "resultPage";
	}
	
//	@RequestMapping(value="/result", method=RequestMethod.POST)
//	public String postResult(@RequestParam(defaultValue="spirngMvc4") String search, Model model) {
//		model.addAttribute("message", "Hello, " + search);
//		return "rediect:/";
//	}
	
}

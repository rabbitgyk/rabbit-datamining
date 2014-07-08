package com.rabbit.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {
	
	@RequestMapping("/test")
	public String index(){
		return "/test/test";
	}

}

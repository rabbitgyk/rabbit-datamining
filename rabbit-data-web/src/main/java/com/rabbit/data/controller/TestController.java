package com.rabbit.data.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {
	
	@RequestMapping("/test")
	public String index(HttpServletRequest request){
		request.setAttribute("username", "rabbit-guoyankui");
		return "/test/test";
	}

}

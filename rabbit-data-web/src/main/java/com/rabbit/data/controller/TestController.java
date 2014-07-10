package com.rabbit.data.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rabbit.data.model.Instance;
import com.rabbit.data.model.Json;

@Controller
@RequestMapping("/")
public class TestController {
	
	@RequestMapping("/test")
	public String test(HttpServletRequest request){
		request.setAttribute("username", "rabbit-guoyankui");
		return "/test/test";
	}
	
	//获取数据可视化页面
	@RequestMapping("/echartDemo")
	public String echartDemo(){
		return "/echarts/demo";
	}
	
	//获取数据
	@RequestMapping("/echartDemo/get")
	@ResponseBody
	public Json getData(){
		Json json = new Json();
		json.setMsg("ok data is it!");
		double[] obj = {2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 102.2, 32.6, 20.0, 6.4, 3.3};
		Instance ins = new Instance();
		ins.setData(obj);
		ins.setNum(5);
		json.setObj(ins);
		return json;
	}
	
	

}

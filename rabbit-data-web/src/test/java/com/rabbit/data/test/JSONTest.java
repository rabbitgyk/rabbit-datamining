package com.rabbit.data.test;

import com.rabbit.data.model.Json;
import com.rabbit.vip.util.JSONUtil;

public class JSONTest {
	
	public static void main(String[] args) {
		Json js1 = new Json();
		js1.setMsg("xiaomin");
		js1.setObj("babbabba");
		js1.setSuccess(true);
		Json js2 = new Json();
		js2.setMsg("xiaohei");
		js2.setObj("cscscscs");
		js2.setSuccess(false);
		Json[] jsjs = new Json[2];
		jsjs[0] = js1;
		jsjs[1] = js2;
		System.out.println(JSONUtil.object2json(jsjs));
	}

}

package com.rabbit.data.controller;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import weka.core.Attribute;
import weka.core.Instances;

import com.rabbit.data.attributeSelection.csg.MICorrelationEval;
import com.rabbit.data.model.Json;
import com.rabbit.data.model.force.ForceData;
import com.rabbit.data.model.force.Link;
import com.rabbit.data.model.force.Node;

/**
 * 力导向布局图
 * @author rabbit
 * @date   Aug 6, 2014
 */

@Controller
@RequestMapping("/")
public class ForceController {
	
	private String filePath = "/home/rabbit/data/my/CountryFlag28.arff";
	
	//获取数据可视化页面
	@RequestMapping("/simpleNet")
	public String echartDemo(){
		return "/echarts/simpleNet";
	}
	
	//获取数据
	@RequestMapping("/simpleNet/get")
	@ResponseBody
	public Json getData(){
		Instances data = getInstances(filePath);
		Json json = new Json();
		json.setMsg("展示数据集中属性之间的关系的数据结构");
		ForceData forceData = null;
		try {
			forceData = getForceData(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		json.setObj(forceData);
		if(forceData != null){
			json.setSuccess(true);
		}else{
			json.setSuccess(false);
		}
		return json;
	}
	
	/**
	 * 获取数据集Instances实例，并默认设置最后一个属性为类属性
	 * @param filePath 数据集存储路径
	 * @return
	 */
	private Instances getInstances(String filePath) {
		Instances instances = null;
		try {
			FileReader frData = new FileReader(filePath);
			instances = new Instances( frData );
		} catch (Exception e) {
			e.printStackTrace();
		}
		instances.setClassIndex( instances.numAttributes()-1 );
		return instances;
	}
	
	/**
	 * 将Instances数据集转化为可以展示的ForceData
	 * @param data
	 * @return
	 * @throws Exception 
	 */
	private ForceData getForceData(Instances data) throws Exception{
		ForceData forceData = new ForceData();
		forceData.setTitle(data.relationName());
		String[] categories = {"条件属性", "类属性"};
		forceData.setCategories(categories);
		
		Node[] nodes = getNodes(data);
		forceData.setNodes(nodes);
		
		Link[] links = getLinks(data, 0.3);
		forceData.setLinks(links);
		return forceData;
	}
	
	/**
	 * 从数据集中获取节点数组
	 * @param data
	 * @return
	 */
	private Node[] getNodes(Instances data){
		int classIndex = data.classIndex();
		int numAttr = data.numAttributes();
		Node[] nodes = new Node[numAttr];
		for(int i=0; i<nodes.length; i++){
			Attribute attr = data.attribute(i);
			Node node = new Node();
			node.setName(attr.name());
			if(attr.index() == classIndex){
				node.setCategory(1);
				node.setValue(2);
			}else{
				node.setCategory(0);
				node.setValue(1);
			}
			nodes[i] = node;
		}
		return nodes;
	}
	
	/**
	 * 从数据集中获取节点之间连接数组
	 * @param data
	 * @param threshold　相关度阈值
	 * @return
	 * @throws Exception
	 */
	private Link[] getLinks(Instances data, double threshold) throws Exception {
		MICorrelationEval eval = new MICorrelationEval();
		eval.buildEvaluator(data);
		double[][] mic = eval.getMICorrMatrix();
		List<Link> linkList = new ArrayList<Link>();
		for(int i=0; i<mic.length; i++){
			for(int j=0; j<mic[i].length; j++){
				if(i != j && mic[i][j] > threshold){
					Link link = new Link();
					link.setSource(i);
					link.setTarget(j);
					int weight = (int)(mic[i][j] * 10);
					if(weight == 0)
						weight = 1;
					link.setWeight(weight);
					linkList.add(link);
				}
			}
		}
		return linkList.toArray(new Link[linkList.size()]);
	}

}

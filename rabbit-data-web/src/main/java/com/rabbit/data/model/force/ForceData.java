package com.rabbit.data.model.force;

/**
 * 力导向布局图的需显示的数据模型
 * @author rabbit
 * @date   Aug 6, 2014
 */
public class ForceData {
	
	private String title;
	private String[] categories;
	private Node[] nodes;
	private Link[] links;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public Node[] getNodes() {
		return nodes;
	}
	public void setNodes(Node[] nodes) {
		this.nodes = nodes;
	}
	public Link[] getLinks() {
		return links;
	}
	public void setLinks(Link[] links) {
		this.links = links;
	}

}

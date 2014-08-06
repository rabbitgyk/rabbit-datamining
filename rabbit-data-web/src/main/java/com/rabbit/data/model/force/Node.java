package com.rabbit.data.model.force;

/**
 * 力导向布局图的节点模型
 * @author rabbit
 * @date   Aug 6, 2014
 */
public class Node {

	private int category;
	private String name;
	private int value;
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}

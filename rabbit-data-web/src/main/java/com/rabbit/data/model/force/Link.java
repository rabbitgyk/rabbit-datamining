package com.rabbit.data.model.force;

/**
 * 力导向布局图的节点之间连线模型
 * @author rabbit
 * @date   Aug 6, 2014
 */
public class Link {
	
	private int source;
	private int target;
	private int weight;
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}

}

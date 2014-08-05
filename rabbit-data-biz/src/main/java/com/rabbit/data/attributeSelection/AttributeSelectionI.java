package com.rabbit.data.attributeSelection;

import weka.core.Instances;

public interface AttributeSelectionI {
	
	/**
	 * 构建属性选择器
	 * @throws Exception
	 */
	public void buildAS() throws Exception;
	/**
	 * 获得选择之后的数据集
	 * @return
	 * @throws Exception
	 */
	public Instances endInstances() throws Exception;

}

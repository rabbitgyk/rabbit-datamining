package com.rabbit.data.classifier;

import java.util.Map;

import weka.core.Instance;

public interface ClassifierI {

	/**
	 * 按照默认的交叉验证的方法，返回分类的评价结果。
	 * @param data 数据集
	 * @return　返回分类结果汇总字符串
	 */
	public String runClassifier() throws Exception;
	/**
	 * 正确率
	 * @return
	 */
	public double pctCorrect() throws Exception;
	/**
	 * 错误率
	 * @return
	 */
	public double pctIncorrect() throws Exception;
	/**
	 * 正确数
	 * @return
	 */
	public int numCorrect() throws Exception;
	/**
	 * 错误数
	 * @return
	 */
	public int numIncorrect() throws Exception;
	
	
	/**
	 * 以上是按照默认交叉验证方式得到的分类器检验结果。
	 * =====================================================================================+
	 * 以下是训练集构建分类器，单个实例测试分类器。
	 */
	
	/**
	 * 构建分类器
	 * @param data
	 * @return
	 */
	public void buildClassifier() throws Exception;
	/**
	 * 判断实例ins属于哪一类，返回类标名
	 * @param ins　需要判断类别的实例
	 * @return　返回所属类标名
	 */
	public String classifyInstance(Instance ins) throws Exception;
	/**
	 * 得到实例ins在各个类标下的概率分布
	 * @param ins
	 * @return
	 */
	public Map<String, Double> distributionForInstance(Instance ins) throws Exception;
	
}

package com.rabbit.data.core;

import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

/**
 * 熵的相关概念计算工具类
 * 
 * @author rabbit
 * @date   Dec 30, 2014
 */
public class EntropyUtil {
	
	/**
	 * 计算数据集中的某个属性的信息熵
	 * @param data 所有的属性都是normal(离散化之后)
	 * @param attr　目标属性
	 * @return
	 * @throws Exception
	 */
	public static double computeEntropy(Instances data, Attribute attr) throws Exception {
		int numInstances = data.numInstances();
		if(numInstances == 0){
			return 0.0;
		}
	    double[] attrCounts = new double[attr.numValues()];
	    Enumeration<Instance> instEnum = data.enumerateInstances();
	    while (instEnum.hasMoreElements()) {
	    	Instance inst = (Instance) instEnum.nextElement();
	    	attrCounts[(int) inst.value(attr)]++;
	    }
	    double entropy = 0;
	    for (int j = 0; j < attr.numValues(); j++) {
	       if (attrCounts[j] > 0) {
	           entropy -= attrCounts[j] * Utils.log2(attrCounts[j]);
	       }
	    }
	    entropy /= (double) numInstances;
	    return entropy + Utils.log2(data.numInstances());
	}
	
	/**
	 * 计算条件信息熵，在attr1的条件下，attr2的信息熵
	 * @param data　所有的属性都是normal(离散化之后)
	 * @param attr1　条件属性
	 * @param attr2　目标属性
	 * @return
	 * @throws Exception
	 */
	public static double computeCondiEntropy(Instances data, Attribute attr1, Attribute attr2) throws Exception{
		int insCount = data.numInstances();
		double entropy = 0.0;
		Enumeration<Object> values = attr1.enumerateValues();
		while(values.hasMoreElements()){
			String value = (String)values.nextElement();
			Instances splitInstances = new Instances(data); //仅包含attr1的一个值的数据子集
			int deletedNum = 0; //记录已经删除的实例个数
			for(int i = 0; i < data.numInstances(); i++){
				Instance ins = data.instance(i);
				if(!ins.stringValue(attr1).equals(value)){
					splitInstances.delete(i - deletedNum);
					deletedNum++;
				}
			}
			//计算数据子集的熵
			double subEntropy = computeEntropy(splitInstances, attr2);
			entropy += (insCount-deletedNum)*subEntropy;
		}
		entropy /= insCount;
		return entropy;
	}
	
	
	/**
	 * 计算两个属性之间的互信息
	 * @param data　离散化之后的数据集
	 * @param attr1
	 * @param attr2
	 * @return
	 */
	public static double computeMI(Instances data, Attribute attr1, Attribute attr2) throws Exception{
		double en2 = computeEntropy(data, attr2);
//		if(en1 == 0.0 || en2 == 0.0)
//			return 0.0;
		double condiEn = EntropyUtil.computeCondiEntropy(data, attr1, attr2);
		return en2 - condiEn;
	}
	
	/**
	 * I((att1;att2)|condiAtt)的条件互信息计算公式
	 * @param data 数据集(离散化数据集)
	 * @param att1 
	 * @param att2
	 * @param condiAtt
	 * @return
	 * @throws Exception 
	 */
	public static double computeCondiMI(Instances data, Attribute att1, Attribute att2, Attribute condiAtt) throws Exception{
		int insCount = data.numInstances();
		double condiMI = 0.0;
		if(att1.equals(condiAtt) || att2.equals(condiAtt)){
			return computeMI(data, att1, att2);
		}
		Enumeration<Object> values = condiAtt.enumerateValues();
		while(values.hasMoreElements()){
			String value = (String)values.nextElement();
			Instances splitInstances = new Instances(data); //仅包含condiAtt的一个值的数据子集
			int deletedNum = 0; //记录已经删除的实例个数
			for(int i = 0; i < data.numInstances(); i++){
				Instance ins = data.instance(i);
				if(!ins.stringValue(condiAtt).equals(value)){
					splitInstances.delete(i - deletedNum);
					deletedNum++;
				}
			}
			//计算数据子集的互信息
			double subMI = computeMI(splitInstances, att1, att2);
			condiMI += (insCount-deletedNum)*subMI;
		}
		condiMI /= insCount;
		
		return condiMI;
	}

}

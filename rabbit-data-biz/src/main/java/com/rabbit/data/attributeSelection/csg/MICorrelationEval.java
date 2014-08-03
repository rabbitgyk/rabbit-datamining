package com.rabbit.data.attributeSelection.csg;

import java.io.FileReader;
import java.util.Enumeration;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;

public class MICorrelationEval {

	/** 待处理的数据instances */
	private Instances m_instances;
	/** 所有属性（包括类属性）的熵 */
	private double[] allAttrEntropy;
	/** 存储相关度的矩阵 */
	private double[][] m_corr_matrix;
	/** 属性的个数 */
	private int m_numAttr;
	/** 相关度的总个数 */
	private int m_numCorr;
	
	public MICorrelationEval(){
		
	}
	
	/**
	 * 初始化相关度矩阵
	 */
	private void initCorrMatrix(){
		m_corr_matrix = new double[m_numAttr][];
	    for (int i = 0; i < m_numAttr; i++) {
	    	m_corr_matrix[i] = new double[i + 1];
	    	m_numCorr += (i + 1);
	    }
	    m_numCorr -= m_numAttr;
	    for (int i = 0; i < m_corr_matrix.length; i++) {
	    	m_corr_matrix[i][i] = 1.0f;
	    }
	    for (int i = 0; i < m_numAttr; i++) {
	      for (int j = 0; j < m_corr_matrix[i].length - 1; j++) {
	        m_corr_matrix[i][j] = -999;
	      }
	    }
	}
	
	public double[][] getMICorrMatrix(){
		return m_corr_matrix;
	}

	public void buildEvaluator(Instances data) throws Exception {
		m_instances = new Instances(data);
		m_numAttr = m_instances.numAttributes();
		initCorrMatrix();
		Instances discInstances = discretize(m_instances);
		computeAllAttrEntropy(discInstances);
		computeAllCorrelation(discInstances);
	}
	
	/**
	 * 原数据集不变得到新的离散化之后的数据集
	 * @param instances
	 * @return
	 * @throws Exception
	 */
	private Instances discretize(Instances instances) throws Exception {
		Instances discInstances = new Instances(instances);
		Discretize disTransform = new Discretize();
	    disTransform.setUseBetterEncoding(true);
	    disTransform.setInputFormat(discInstances);
	    discInstances = Filter.useFilter(discInstances, disTransform);
	    return discInstances;
	}
	
	/**
	 * 计算数据集中的某个属性的信息熵
	 * @param data 所有的属性都是normal(离散化之后)
	 * @param attr　目标属性
	 * @return
	 * @throws Exception
	 */
	private double computeEntropy(Instances data, Attribute attr) throws Exception {
		 
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
	    entropy /= (double) data.numInstances();
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
	private double computeCondiEntropy(Instances data, Attribute attr1, Attribute attr2) throws Exception{
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
	 * 计算所有属性(包括类属性)的信息熵
	 * @param data 离散化之后的数据集
	 */
	private void computeAllAttrEntropy(Instances data) throws Exception{
		int numAttr = data.numAttributes();
		allAttrEntropy = new double[numAttr];
		for(int i = 0; i < numAttr; i++){
			allAttrEntropy[i] = computeEntropy(data, data.attribute(i));
		}
	}
	
	/**
	 * 计算两个属性之间的相关度
	 * @param data　离散化之后的数据集
	 * @param attr1
	 * @param attr2
	 * @return
	 */
	private double computeCorrelation(Instances data, Attribute attr1, Attribute attr2) throws Exception{
		double en1 = allAttrEntropy[attr1.index()];
		double en2 = allAttrEntropy[attr2.index()];
		if(en1 == 0.0 || en2 == 0.0)
			return 0.0;
		double condiEn = computeCondiEntropy(data, attr1, attr2);
		return 2*(en2 - condiEn)/(en1 + en2);
	}
	
	/**
	 * 计算所有属性之间的相关度
	 * @param data
	 */
	private void computeAllCorrelation(Instances data) throws Exception{
		for(int i = 0; i < m_numAttr; i++){
			for(int j =0; j < m_corr_matrix[i].length; j++){
				if(i != j){
					m_corr_matrix[i][j] = computeCorrelation(data, data.attribute(i), data.attribute(j));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		FileReader frData = new FileReader("/home/rabbit/data/my/wdbc31.arff");
		Instances instances = new Instances( frData );
		instances.setClassIndex( instances.numAttributes()-1 );
		
		MICorrelationEval mic = new MICorrelationEval();
		mic.buildEvaluator(instances);
		
	}

}




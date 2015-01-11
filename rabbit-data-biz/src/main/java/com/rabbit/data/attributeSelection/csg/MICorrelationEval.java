package com.rabbit.data.attributeSelection.csg;

import java.io.FileReader;

import com.rabbit.data.core.DiscretizeUtil;
import com.rabbit.data.core.EntropyUtil;

import weka.core.Attribute;
import weka.core.Instances;
/**
 * 一种基于互信息的相关度表示方法
 * 2*MI(1,2)/(en1 + en2)
 * 
 * @author rabbit
 * @date   Dec 30, 2014
 */
public class MICorrelationEval {

	/** 待处理的数据instances */
	private Instances m_instances;
	/** 所有属性（包括类属性）的熵 */
	private double[] allAttrEntropy;
	/** 存储相关度的矩阵 */
	private double[][] m_corr_matrix;
	/** 属性的个数 */
	private int m_numAttr;
	
	public MICorrelationEval(){
		
	}
	
	/**
	 * 初始化相关度矩阵
	 */
	private void initCorrMatrix(){
		m_corr_matrix = new double[m_numAttr][];
	    for (int i = 0; i < m_numAttr; i++) {
	    	m_corr_matrix[i] = new double[i + 1];
	    }
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
		Instances discInstances = DiscretizeUtil.discretize(m_instances);
		computeAllAttrEntropy(discInstances);
		computeAllCorrelation(discInstances);
	}
	
	/**
	 * 计算所有属性(包括类属性)的信息熵
	 * @param data 离散化之后的数据集
	 */
	private void computeAllAttrEntropy(Instances data) throws Exception{
		int numAttr = data.numAttributes();
		allAttrEntropy = new double[numAttr];
		for(int i = 0; i < numAttr; i++){
			allAttrEntropy[i] = EntropyUtil.computeEntropy(data, data.attribute(i));
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
		double condiEn = EntropyUtil.computeCondiEntropy(data, attr1, attr2);
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
		double[][] corr = mic.getMICorrMatrix();
		for(int i = 0; i < corr.length; i++){
			for(int j =0; j < corr[i].length; j++){
				System.out.print(corr[i][j] + "  ");
			}
			System.out.println();
		}
	}

}




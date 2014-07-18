package com.rabbit.data.start;

import java.io.FileReader;

import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;
import weka.core.Instances;

public class WekaTest {
	
	public static void main(String[] args) {
		Instances ins = null;
		FileReader fr = null;
		try {
			fr = new FileReader("/home/rabbit/data/ionosphere.arff");
			ins = new Instances(fr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int num_in = ins.numInstances();
		int num_attr = ins.numAttributes();
		System.out.println("num_in:"+num_in);
		System.out.println("num_attr:"+num_attr);
		ins.setClassIndex(num_attr-1);
//		System.out.println(ins);
//		System.out.println(ins.instance(1).classIsMissing());
		
//		//测试离散化
//		Discretize m_Disc = new Discretize();
//		String[] options = new String[4]; 
//		options[0] = "-R";
//		options[1] = "6";
//		options[2] = "-precision";
//		options[3] = "6";
////		options[3] = "-D";
////		options[4] = "-Y";
////		options[5] = "-E";
////		options[6] = "-K";
//		
//	    try {
//	    	m_Disc.setOptions(options);
//	    	m_Disc.setInputFormat(ins);
//			ins = weka.filters.Filter.useFilter(ins, m_Disc);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    System.out.println(ins);
		
	    //属性选择
		//初始化搜索算法（search method）及属性评测算法（attribute evaluator） 
		Ranker rank = new Ranker(); 
        InfoGainAttributeEval eval = new InfoGainAttributeEval();
        try {
        	//根据评测算法评测各个属性
			eval.buildEvaluator(ins);
			//按照特定搜索算法对属性进行筛选
			int[] attrIndex = rank.search(eval, ins);
			System.out.println("shuxing number:" + attrIndex.length);
			for(int i=0; i<attrIndex.length; i++){
				System.out.println(attrIndex[i]);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}

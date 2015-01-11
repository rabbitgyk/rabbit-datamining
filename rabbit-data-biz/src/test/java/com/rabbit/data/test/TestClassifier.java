package com.rabbit.data.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import weka.core.Instance;
import weka.core.Instances;

import com.rabbit.data.attributeSelection.AttributeSelectionI;
import com.rabbit.data.attributeSelection.ConnectSubGraphImpl;
import com.rabbit.data.attributeSelection.GreedyStepwiseImpl;
import com.rabbit.data.attributeSelection.SampleOneImpl;
import com.rabbit.data.classifier.ClassifierI;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

public class TestClassifier {
	
	public static void main(String[] args) throws Exception {
		FileReader frData = new FileReader("/home/rabbit/data/ionosphere.arff");
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);
		
//		System.out.println("总属性个数：" + data.numAttributes()+data.relationName());
//		System.out.println(eval(data));
		
//		//-------------------原始数据集-----------------------------------------
//		
//		NaiveBayesImpl nbi = new NaiveBayesImpl(data);
//		String summary = nbi.runClassifier();
//		System.out.println("NaiveBayesImpl:" + nbi.pctCorrect());
//		
//		J48Impl j48i = new J48Impl(data);
//		String summaryJ = j48i.runClassifier();
//		System.out.println("J48Impl:" + j48i.pctCorrect());
//		
//		//-------------------极大连通子图----------------------------------------
//		
//		ConnectSubGraphImpl csgi = new ConnectSubGraphImpl(data);
//		NaiveBayesImpl nbi_csg = new NaiveBayesImpl();
//		csgi.setEvalClassifier(nbi_csg);
//		csgi.buildAS();
//		Instances csg_nbi_data = csgi.endInstances();
//		System.out.println("ConnectSubGraphImpl attrs:"+csg_nbi_data.numAttributes());
//		nbi_csg.setData(csg_nbi_data);
//		String summary1 = nbi_csg.runClassifier();
//		System.out.println("NaiveBayesImpl: " + nbi_csg.pctCorrect());
//		
//		ConnectSubGraphImpl csgi1 = new ConnectSubGraphImpl(data);
//		J48Impl j48i_csg = new J48Impl();
//		csgi1.setEvalClassifier(j48i_csg);
//		csgi1.buildAS();
//		Instances csg_j48i_data = csgi1.endInstances();
//		System.out.println("ConnectSubGraphImpl attrs:"+csg_j48i_data.numAttributes());
//		j48i_csg.setData(csg_j48i_data);
//		String summaryJ1 = j48i_csg.runClassifier();
//		System.out.println("J48Impl:" + j48i_csg.pctCorrect());
//		
//		//-----------------------简单相关度属性选择算法---------------------------------------------------
//		
//		SampleOneImpl soi = new SampleOneImpl(data);
//		NaiveBayesImpl nbi_soi = new NaiveBayesImpl();
//		soi.setEvalClassifier(nbi_soi);
//		soi.buildAS();
//		Instances soi_nbi_data = soi.endInstances();
//		System.out.println("SampleOneImpl attrs:"+soi_nbi_data.numAttributes());
//		nbi_soi.setData(soi_nbi_data);
//		String summary11 = nbi_soi.runClassifier();
//		System.out.println("NaiveBayesImpl:" + nbi_soi.pctCorrect());
//		
//		SampleOneImpl soi1 = new SampleOneImpl(data);
//		J48Impl j48i_soi = new J48Impl();
//		soi1.setEvalClassifier(j48i_soi);
//		soi1.buildAS();
//		Instances soi_j48i_data = soi1.endInstances();
//		System.out.println("SampleOneImpl attrs:"+soi_j48i_data.numAttributes());
//		j48i_soi.setData(soi_j48i_data);
//		String summaryJ12 = j48i_soi.runClassifier();
//		System.out.println("J48Impl:" + j48i_soi.pctCorrect());
		
		//-----------------------------------------------------------
		
//		GreedyStepwiseImpl gsi = new GreedyStepwiseImpl(data);
//		gsi.buildAS();
//		Instances newData2 = gsi.endInstances();
//		System.out.println("GreedyStepwiseImpl attrs:"+newData2.numAttributes());
//		
//		NaiveBayesImpl nbi2 = new NaiveBayesImpl(newData2);
//		String summary2 = nbi2.runClassifier();
//		System.out.println("NaiveBayesImpl:" + summary2);
//		
//		J48Impl j48i2 = new J48Impl(newData2);
//		String summaryJ2 = j48i2.runClassifier();
//		System.out.println("J48Impl:" + summaryJ2);
	}
	
//	public void testClassifier(AttributeSelectionI asi, ClassifierI ci){
//		
//		ConnectSubGraphImpl csgi = new ConnectSubGraphImpl(data);
//		NaiveBayesImpl nbi_csg = new NaiveBayesImpl();
//		a.setEvalClassifier(nbi_csg);
//		csgi.buildAS();
//		Instances csg_nbi_data = csgi.endInstances();
//		System.out.println("ConnectSubGraphImpl attrs:"+csg_nbi_data.numAttributes());
//		nbi_csg.setData(csg_nbi_data);
//		String summary1 = nbi_csg.runClassifier();
//		System.out.println("NaiveBayesImpl:" + summary1);
//		
//	}
	
	

}

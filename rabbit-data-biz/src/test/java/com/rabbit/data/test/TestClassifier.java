package com.rabbit.data.test;

import java.io.FileReader;
import java.util.Map;

import weka.core.Instance;
import weka.core.Instances;

import com.rabbit.data.attributeSelection.ConnectSubGraphImpl;
import com.rabbit.data.attributeSelection.GreedyStepwiseImpl;
import com.rabbit.data.classifier.NaiveBayesImpl;

public class TestClassifier {
	
	public static void main(String[] args) throws Exception {
		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag28.arff");
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);
		NaiveBayesImpl nbi = new NaiveBayesImpl(data);
		String summary = nbi.runClassifier();
		System.out.println(summary);
		
		ConnectSubGraphImpl csgi = new ConnectSubGraphImpl(data);
		csgi.buildAS();
		Instances newData1 = csgi.endInstances();
		System.out.println("ConnectSubGraphImpl attrs:"+newData1.numAttributes());
		NaiveBayesImpl nbi1 = new NaiveBayesImpl(newData1);
		String summary1 = nbi1.runClassifier();
		System.out.println(summary1);
		
		GreedyStepwiseImpl gsi = new GreedyStepwiseImpl(data);
		gsi.buildAS();
		Instances newData2 = gsi.endInstances();
		System.out.println("GreedyStepwiseImpl attrs:"+newData2.numAttributes());
		NaiveBayesImpl nbi2 = new NaiveBayesImpl(newData2);
		String summary2 = nbi2.runClassifier();
		System.out.println(summary2);
	}

}

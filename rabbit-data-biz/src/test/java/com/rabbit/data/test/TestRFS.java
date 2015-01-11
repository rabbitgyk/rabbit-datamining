package com.rabbit.data.test;

import java.io.FileReader;

import com.rabbit.data.attributeSelection.RayleighFSImpl;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

import weka.core.Instances;

public class TestRFS {

	public static void main(String[] args) throws Exception {
		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag28.arff");
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);
		
//		NaiveBayesImpl nbi = new NaiveBayesImpl(data);
//		String summary = nbi.runClassifier();
//		System.out.println("NaiveBayesImpl:" + nbi.pctCorrect());
//		
//		J48Impl j48i = new J48Impl(data);
//		String summaryJ = j48i.runClassifier();
//		System.out.println("J48Impl:" + j48i.pctCorrect());
		System.out.println(data.relationName());
		
		RayleighFSImpl rfsi = new RayleighFSImpl(data, new NaiveBayesImpl());
		rfsi.buildAS();
		NaiveBayesImpl nbi_rfsi = new NaiveBayesImpl(rfsi.endInstances());
		nbi_rfsi.runClassifier();
		System.out.println("NaiveBayesImpl nbi_rfsi:" + nbi_rfsi.pctCorrect());
		
		RayleighFSImpl rfsi1 = new RayleighFSImpl(data, new J48Impl());
		rfsi1.buildAS();
		J48Impl j48i_rfsi1 = new J48Impl(rfsi1.endInstances());
		j48i_rfsi1.runClassifier();
		System.out.println("J48Impl j48i_rfsi1:" + j48i_rfsi1.pctCorrect());
	}
}

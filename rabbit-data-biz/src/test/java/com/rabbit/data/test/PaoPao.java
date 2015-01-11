package com.rabbit.data.test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.rabbit.data.attributeSelection.SampleOneImpl;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

import weka.core.Instances;

public class PaoPao {
	
	public static void main(String[] args) throws Exception {
		String[] datas = {"ionosphere.arff","soybean.arff","my/waveform21.arff","my/waveform40.arff",
				"my/wdbc31.arff","my/CountryFlag28.arff"};
		
		String set = "my/CountryFlag28.arff";
		double beta = 0.48;
		
		FileReader frData = new FileReader("/home/rabbit/data/"+set);
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);
		
		SampleOneImpl soi = new SampleOneImpl(data);
		soi.buildASB(beta);
		Instances endData = soi.endInstances();
		
		NaiveBayesImpl nbi = new NaiveBayesImpl();
//		J48Impl nbi = new J48Impl();
		
		nbi.setData(endData);
		nbi.runClassifier();
		System.out.println("att number: "+endData.numAttributes());
		System.out.println("\nNaiveBayesImpl:" + nbi.pctCorrect());
	}

}

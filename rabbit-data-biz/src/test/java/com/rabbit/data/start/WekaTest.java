package com.rabbit.data.start;

import java.io.FileReader;

import weka.core.Instances;

public class WekaTest {
	
	public static void main(String[] args) {
		Instances ins = null;
		FileReader fr = null;
		try {
			fr = new FileReader("/home/rabbit/data/weather.numeric.arff");
			ins = new Instances(fr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int num_in = ins.numInstances();
		int num_attr = ins.numAttributes();
		System.out.println("num_in:"+num_in);
		System.out.println("num_attr:"+num_attr);
		ins.setClassIndex(num_attr-1);
		System.out.println(ins.instance(1).classIsMissing());
	}

}

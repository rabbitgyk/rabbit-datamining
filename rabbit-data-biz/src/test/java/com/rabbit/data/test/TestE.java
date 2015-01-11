package com.rabbit.data.test;

import java.io.FileReader;

import weka.core.Attribute;
import weka.core.Instances;

import com.rabbit.data.core.DiscretizeUtil;
import com.rabbit.data.core.EntropyUtil;

public class TestE {

	public static void main(String[] args) throws Exception {
		FileReader frData = new FileReader("/home/rabbit/data/my/wdbc31.arff");
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);

		Instances discData = DiscretizeUtil.discretize(data);
		
		double mi = EntropyUtil.computeMI(discData, discData.attribute(0), discData.attribute(2));
//		Attribute att = discData.attribute(10);
//		System.out.println(att.numValues());
//		System.out.println(att.isNumeric());
//		double mi = EntropyUtil.computeEntropy(discData, att);
		System.out.println("MI: " + mi);
		
		
		
	}
}

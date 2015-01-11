package com.rabbit.data.test;

import java.io.FileReader;

import com.rabbit.data.attributeSelection.qpfs.CondiMIEval;
import com.rabbit.data.attributeSelection.qpfs.RayleighFS;
import com.rabbit.data.core.DiscretizeUtil;
import com.rabbit.data.core.EntropyUtil;

import weka.core.Instances;

public class TestCondiMI {

	public static void main(String[] args) throws Exception{
		FileReader frData = new FileReader("/home/rabbit/data/ionosphere.arff");
		Instances data = new Instances(frData);
		data.setClassIndex(data.numAttributes() - 1);
//		Instances ins = DiscretizeUtil.discretize(data);
		System.out.println(data.numAttributes());
		CondiMIEval cme = new CondiMIEval(data);
		cme.computeMatrix();
		double[][] m = cme.getMatrix();
//		for(int i=0; i<m.length; i++){
//			for(int j=0; j<m[i].length; j++){
//				System.out.print(m[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		RayleighFS rfs = new RayleighFS(data);
//		rfs.computeEigen(m);
		
//		System.out.println(rfs.getEigenVectors().toString());
		double[] vas = rfs.getEigenValues();
		for(int i=0; i<vas.length; i++){
			System.out.print(vas[i]+" ");
		}
		System.out.println();
		
		System.out.println(rfs.mainEigenValue());
		double[] vec = rfs.mainEigenVector();
		for(int i=0; i<vec.length; i++){
			System.out.print(vec[i]+" ");
		}
		
		System.out.println();
		int[] orders = rfs.sortAtts();
		for(int i=0; i<orders.length; i++){
			System.out.print(orders[i]+" ");
		}
		System.out.println();
		System.out.println(rfs.getEndInstances(4));
	}
}

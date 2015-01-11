package com.rabbit.data.attributeSelection;

import com.rabbit.data.attributeSelection.csg.SampleOneFS;
import com.rabbit.data.classifier.ClassifierI;

import weka.core.Instances;

public class SampleOneImpl implements AttributeSelectionI{

	private Instances instances;
	private SampleOneFS so;
	private final double alpha0 = 0.001;
	private double beta0 = 0.01;
	
	private ClassifierI classifier;
	
	double maxBeta;
	
	public SampleOneImpl(Instances data){
		instances = data;
	}
	
	/**
	 * 设置评价的分类器
	 * @param ci
	 */
	public void setEvalClassifier(ClassifierI ci){
		this.classifier = ci;
	}
	
	@Override
	public void buildAS() throws Exception {
		so = new SampleOneFS(instances, alpha0, beta0);
		double maxpct = 0.0;
		maxBeta = beta0;
		while(beta0 < 1.0){
			so.setAttrThreshold(beta0);
			so.search();
//			NaiveBayesImpl nbi = new NaiveBayesImpl(csg.getEndInstances());
			classifier.setData(so.getEndInstances());
			classifier.runClassifier();
			if(classifier.pctCorrect() > maxpct){
				maxpct = classifier.pctCorrect();
				maxBeta = beta0;
			}
			beta0 = beta0 + 0.01;
		}
		
//		System.out.println("maxBeta: "+maxBeta);
		so.setAttrThreshold(maxBeta);
		so.search();
	}
	
	/**
	 * 指定阈值beta
	 * @param beta
	 * @throws Exception
	 */
	public void buildASB(double beta) throws Exception{
		so = new SampleOneFS(instances, alpha0, beta0);
		so.setAttrThreshold(beta);
		so.search();
	}

	@Override
	public Instances endInstances() throws Exception {
		return so.getEndInstances();
	}
	
	public double getMaxBeta(){
		return maxBeta;
	}

}

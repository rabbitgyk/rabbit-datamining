package com.rabbit.data.classifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;

public class NaiveBayesImpl implements ClassifierI{
	
	private Instances instances;
	private NaiveBayes classifier;
	private Evaluation eval;
	
	public NaiveBayesImpl() throws Exception{
		classifier = new NaiveBayes();
	}
	
	public NaiveBayesImpl(Instances data) throws Exception{
		instances = data;
		classifier = new NaiveBayes();
	}
	
	@Override
	public void setData(Instances data){
		this.instances = data;
	}

	@Override
	public String runClassifier() throws Exception {
		eval = new Evaluation(instances);
		eval.crossValidateModel(classifier, instances, 10, new Random(1));
		return eval.toSummaryString("\nClassify Results:", false);
	}

	@Override
	public double pctCorrect() throws Exception {
		if(eval != null)
			return eval.pctCorrect();
		else
			throw new Exception("evaluation 未初始化.");
	}

	@Override
	public double pctIncorrect() throws Exception {
		if(eval != null)
			return eval.pctIncorrect();
		else
			throw new Exception("evaluation 未初始化.");
	}

	@Override
	public int numCorrect() throws Exception {
		if(eval != null)
			return (int) eval.correct();
		else
			throw new Exception("evaluation 未初始化.");
	}

	@Override
	public int numIncorrect() throws Exception {
		if(eval != null)
			return (int) eval.incorrect();
		else
			throw new Exception("evaluation 未初始化.");
	}

	@Override
	public void buildClassifier() throws Exception {
		classifier.buildClassifier(instances);
	}

	@Override
	public String classifyInstance(Instance ins) throws Exception {
		double index = classifier.classifyInstance(ins);
		return instances.classAttribute().value((int)index);
	}

	@Override
	public Map<String, Double> distributionForInstance(Instance ins) throws Exception {
		int numClass = instances.numClasses();
		Map<String, Double> map = new HashMap<String, Double>();
		double[] disClass = classifier.distributionForInstance(ins);
		for(int i=0; i<numClass; i++){
			String clazz = instances.classAttribute().value(i);
			map.put(clazz, disClass[i]);
		}
		return map;
	}

}

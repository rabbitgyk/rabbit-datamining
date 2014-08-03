package com.rabbit.data.attributeSelection;

import java.io.FileReader;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class ASComparation {
	private Instances m_instances = null;
	
	/**
	 * 从文件获取数据集实例
	 * @param fileName
	 * @throws Exception
	 */
    public void readFileInstances(String fileName) throws Exception {
        FileReader frData = new FileReader(fileName);
        m_instances = new Instances(frData);
        m_instances.setClassIndex(m_instances.numAttributes() - 1);
    }
    
    /**
     * NaiveBayes分类器
     * @throws Exception 
     */
    public void classifierNB() throws Exception{
    	NaiveBayes classifier = new NaiveBayes();
    	//classifier.buildClassifier(m_instances);
    	//System.out.println(Evaluation.evaluateModel(classifier, new String[0]));
    	Evaluation eval = new Evaluation(m_instances);
    	eval.crossValidateModel(classifier, m_instances, 10, new Random(1));
    	System.out.println("correct:"+eval.correct());
    	System.out.println("errorRate:"+eval.errorRate());
    	System.out.println(eval.toSummaryString("\nResults\n\n", false));
    }
    
    public static void main(String[] args) {
		ASComparation asc = new ASComparation();
		try {
			asc.readFileInstances("/home/rabbit/data/my/CountryFlag25.arff");
			asc.classifierNB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

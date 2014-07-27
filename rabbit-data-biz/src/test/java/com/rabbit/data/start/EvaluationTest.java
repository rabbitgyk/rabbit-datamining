package com.rabbit.data.start;

import java.io.FileReader;
import java.util.Random;
 
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
 
public class EvaluationTest{
	
	private Instances m_instances = null;
   
    public void getFileInstances( String fileName ) throws Exception
    {
        FileReader frData = new FileReader( fileName );
        m_instances = new Instances( frData );
       
        m_instances.setClassIndex( m_instances.numAttributes() - 1 );
    }
   
    public void crossValidation() throws Exception
    {
        J48 classifier = new J48();
        //NaiveBayes classifier = new NaiveBayes();
        //SMO classifier = new SMO();
       
        Evaluation eval = new Evaluation( m_instances );
        eval.crossValidateModel( classifier, m_instances, 10, new Random(1));
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }
   
    public void evaluateTestData() throws Exception
    {
        J48 classifier = new J48();
        //NaiveBayes classifier = new NaiveBayes();
        //SMO classifier = new SMO();
       
        classifier.buildClassifier( m_instances );
       
        Evaluation eval = new Evaluation( m_instances );
        eval.evaluateModel( classifier, m_instances );
        System.out.println(eval.toClassDetailsString());
        System.out.println(eval.toSummaryString());
        System.out.println(eval.toMatrixString());
    }
   
    public static void main( String[] args ) throws Exception
    {
        EvaluationTest etest = new EvaluationTest();
       
        etest.getFileInstances( "/home/rabbit/data/contact-lenses.arff");
        etest.crossValidation();
        System.out.println( "***********************************\n\n" );
        etest.evaluateTestData();
    }
}

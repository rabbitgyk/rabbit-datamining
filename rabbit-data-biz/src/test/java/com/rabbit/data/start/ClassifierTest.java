package com.rabbit.data.start;

import java.io.FileReader;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
 
public class ClassifierTest
{
    private Instances m_instances = null;
   
    public void getFileInstances( String fileName ) throws Exception
    {
        FileReader frData = new FileReader( fileName );
        m_instances = new Instances( frData );
        System.out.println(m_instances);
        m_instances.setClassIndex( m_instances.numAttributes() - 1 );
    }
   
    public void classify() throws Exception
    {
        J48 classifier = new J48();
        //NaiveBayes classifier = new NaiveBayes();
        //SMO classifier = new SMO();
       
        classifier.buildClassifier( m_instances );
        System.out.println( classifier.classifyInstance( m_instances.instance( 0 ) ) );
        double[] dis = classifier.distributionForInstance(m_instances.instance(3));
        for(double i : dis){
        	System.out.println("dis:"+i);
        }
    }
   
    public static void main( String[] args ) throws Exception
    {
        ClassifierTest ctest = new ClassifierTest();
        ctest.getFileInstances( "/home/rabbit/data/weather.numeric.arff");
        ctest.classify();
        System.out.println(ctest.m_instances.instance(2).attribute(0).isNominal());
    }
}
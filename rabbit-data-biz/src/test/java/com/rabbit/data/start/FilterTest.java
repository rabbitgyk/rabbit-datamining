package com.rabbit.data.start;

import java.io.FileReader;
import java.util.Random;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
 
public class FilterTest
{
    private Instances m_instances = null;
   
    public void getFileInstances( String fileName ) throws Exception
    {
        FileReader frData = new FileReader( fileName );
        m_instances = new Instances( frData );
        m_instances.setClassIndex( m_instances.numAttributes() - 1 );
        Attribute attr = m_instances.attribute(0);
        System.out.println(m_instances.instance(3).value(attr));
    }
   
    public void selectAttUseFilter() throws Exception
    {
        AttributeSelection filter = new AttributeSelection();  // package weka.filters.supervised.attribute!
        CfsSubsetEval eval = new CfsSubsetEval();
        GreedyStepwise search = new GreedyStepwise();
        String[] gsOption = Utils.splitOptions("-P 1,5-7,9 -N 10");
        search.setOptions(gsOption);
//        ConnectSubGraph search = new ConnectSubGraph();
        System.out.println("num to select : "+search.getNumToSelect());
        filter.setEvaluator(eval);
        filter.setSearch(search);
        filter.setInputFormat( m_instances );
       
        System.out.println( "number of instance attribute = " + m_instances.numAttributes() );
       
        Instances selectedIns = Filter.useFilter( m_instances, filter);
        System.out.println("selected attributes:" + selectedIns.enumerateAttributes().nextElement());
        System.out.println( "number of selected instance attribute = " + selectedIns.numAttributes() );
    }
   
    public void selectAttUseMC() throws Exception
    {  
         AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
         CfsSubsetEval eval = new CfsSubsetEval();
         GreedyStepwise search = new GreedyStepwise();
         J48 base = new J48();
         classifier.setClassifier( base );
         classifier.setEvaluator( eval );
         classifier.setSearch( search );
         // 10-fold cross-validation
         Evaluation evaluation = new Evaluation( m_instances );
         evaluation.crossValidateModel(classifier, m_instances, 10, new Random(1));
         System.out.println( evaluation.toSummaryString() );
    }
   
    public static void main( String[] args ) throws Exception
    {
        FilterTest filter = new FilterTest();
        filter.getFileInstances( "/home/rabbit/data/weather.numeric.arff");
        filter.selectAttUseFilter();
//        filter.selectAttUseMC();
    }
 
}

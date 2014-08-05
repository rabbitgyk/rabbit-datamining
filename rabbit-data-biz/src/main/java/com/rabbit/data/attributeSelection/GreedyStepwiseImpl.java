package com.rabbit.data.attributeSelection;

import java.io.FileReader;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
/**
 * 利用贪婪逐步搜索算法的基于相关度的属性选择算法
 * @author rabbit
 * @date   Aug 5, 2014
 */
public class GreedyStepwiseImpl implements AttributeSelectionI{

	private Instances instances;
	private AttributeSelection filter;
	
	public GreedyStepwiseImpl(Instances data){
		instances = data;
	}
	
	@Override
	public void buildAS() throws Exception {
		filter = new AttributeSelection();  // package weka.filters.supervised.attribute!
		CfsSubsetEval eval = new CfsSubsetEval();
		GreedyStepwise search = new GreedyStepwise();
		String[] gsOption = Utils.splitOptions("-N 10");
        search.setOptions(gsOption);
		filter.setEvaluator(eval);
		filter.setSearch(search);
		filter.setInputFormat(instances);
	}

	@Override
	public Instances endInstances() throws Exception {
		return Filter.useFilter(instances, filter);
	}
	
//	public static void main(String[] args) throws Exception {
//		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag28.arff");
//		Instances instances = new Instances( frData );
//		instances.setClassIndex( instances.numAttributes()-1 );
//		GreedyStepwiseImpl gsi = new GreedyStepwiseImpl(instances);
//		gsi.buildAS();
//		Instances in = gsi.endInstances();
//		System.out.println(in.numAttributes());
//	}

}

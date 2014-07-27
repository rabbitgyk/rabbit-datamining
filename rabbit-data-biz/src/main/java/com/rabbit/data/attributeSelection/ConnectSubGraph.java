package com.rabbit.data.attributeSelection;

import java.util.Enumeration;

import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.RankedOutputSearch;
import weka.attributeSelection.StartSetHandler;
import weka.core.Instances;
import weka.core.Option;
import weka.core.OptionHandler;


public class ConnectSubGraph extends ASSearch implements RankedOutputSearch, StartSetHandler, OptionHandler {

	private static final long serialVersionUID = -7484636545777319895L;

	@Override
	public Enumeration<Option> listOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOptions(String[] options) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartSet(String startSet) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStartSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[][] rankedAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setThreshold(double threshold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getThreshold() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setNumToSelect(int numToSelect) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumToSelect() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCalculatedNumToSelect() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGenerateRanking(boolean doRanking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getGenerateRanking() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int[] search(ASEvaluation ASEvaluator, Instances data)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}

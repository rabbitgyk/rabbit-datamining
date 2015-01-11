package com.rabbit.data.attributeSelection;

import com.rabbit.data.attributeSelection.qpfs.RayleighFS;
import com.rabbit.data.classifier.ClassifierI;

import weka.core.Instances;

public class RayleighFSImpl implements AttributeSelectionI {

	private Instances data = null;
	private RayleighFS rfs = null;
	//评价分类器
	private ClassifierI classifier;
	//得到的属性个数
	int attK = 0;
	
	public RayleighFSImpl(Instances data, ClassifierI ci) throws Exception {
		this.data = data;
		this.classifier = ci;
	}
	
	
	@Override
	public void buildAS() throws Exception {
		rfs = new RayleighFS(data);
		int numAtt = data.numAttributes();
		double maxPct = 0.0;
		int maxK = 0;
		for(int i=1; i<numAtt; i++){
			Instances end = rfs.getEndInstances(i);
			classifier.setData(end);
			classifier.runClassifier();
			if(classifier.pctCorrect() > maxPct){
				maxPct = classifier.pctCorrect();
				maxK = i;
			}
		}
		System.out.println("maxPct: "+maxPct+"   "+"maxK: "+maxK);
		attK = maxK;
	}

	@Override
	public Instances endInstances() throws Exception {
		//返回最好的属性子集
		return rfs.getEndInstances(attK);
	}

}

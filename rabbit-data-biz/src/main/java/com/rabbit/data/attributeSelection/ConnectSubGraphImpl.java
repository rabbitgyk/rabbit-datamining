package com.rabbit.data.attributeSelection;

import java.io.FileReader;

import com.rabbit.data.attributeSelection.csg.ConnectSubGraph;

import weka.core.Instances;

/**
 * 基于极大连通子图的相关度属性选择算法
 * @author rabbit
 * @date   Aug 5, 2014
 */
public class ConnectSubGraphImpl implements AttributeSelectionI{

	private Instances instances;
	private ConnectSubGraph csg;
	private final double alpha0 = 0.001;
	private final double beta0 = 0.02;
	
	public ConnectSubGraphImpl(Instances data){
		instances = data;
	}
	
	@Override
	public void buildAS() throws Exception {
		csg = new ConnectSubGraph(instances, alpha0, beta0);
		csg.search();
	}

	@Override
	public Instances endInstances() throws Exception {
		return csg.getEndInstances();
	}
	
//	private void printCorr(double[][] m_corr_matrix){
//		for(int i = 0; i < m_corr_matrix.length; i++){
//			for(int j =0; j < m_corr_matrix[i].length; j++){
//				System.out.print(m_corr_matrix[i][j] + "  ");
//			}
//			System.out.println();
//		}
//	}
//	
//	public static void main(String[] args) throws Exception {
//		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag28.arff");
//		Instances instances = new Instances( frData );
//		instances.setClassIndex( instances.numAttributes()-1 );
//		
//		ConnectSubGraphImpl csgi = new ConnectSubGraphImpl(instances);
//		csgi.buildAS();
//	}

}

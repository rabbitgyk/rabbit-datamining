package com.rabbit.data.attributeSelection;

import java.io.FileReader;
import java.util.BitSet;

import weka.core.Instances;


public class ConnectSubGraph {

	/** 生成极大连通子图的相关度阈值 */
	private double m_threshold0;
	private double m_threshold;
	private BitSet delAttrGroup;
	private Instances m_instances;
	private int m_numAttr;
	private int m_classIndex;
	private double[][] m_corr_matrix;
	
	private boolean debug = false;
	
	public ConnectSubGraph(double threshold0, double threshold) {
		m_threshold0 = threshold0;
		m_threshold = threshold;
	}

	/**
	 * 根据相关度矩阵选择属性(threshold是相关度阈值)(基于连通图的)
	 * 删除多余的条件属性，使条件属性之间的相关度最低（方法：删除联通子图中度数最大的顶点）
	 * @param data
	 * @throws Exception
	 */
	public void search(Instances data)	throws Exception {
		m_numAttr = data.numAttributes();
		m_classIndex = data.classIndex();
		m_instances = new Instances(data);
		MICorrelationEval mic = new MICorrelationEval();
		mic.buildEvaluator(data);
		m_corr_matrix = mic.getMICorrMatrix();
		if(debug)
			printCorr();
		if(delAttrGroup == null)
			delAttrGroup = new BitSet(m_numAttr);
		//删除与类属性不相关的属性
		for(int i=0; i<m_numAttr; i++){
			double corr = getCorrelation(m_classIndex, i);
			if(corr < m_threshold0)
				delAttrGroup.set(i);
		}
		if(debug)
			System.out.println("不相关的属性："+delAttrGroup);
		//删除冗余的属性
		boolean flag = true;
		while(flag){
			//记住度数最大的顶点和最大度
			int maxAttr = 0;
			int maxDushu = 0;
			for(int i=0; i<m_numAttr; i++){
				int dushu = 0;
				for(int j=0;j<m_numAttr; j++){
					double corre = -999;
					if(i != m_classIndex && j != m_classIndex && i != j 
							&& !delAttrGroup.get(i) && !delAttrGroup.get(j))
						corre = getCorrelation(i, j);
					if(corre > m_threshold)
						dushu++;
				} //end for j
				if(dushu > maxDushu){
					maxDushu = dushu;
					maxAttr = i;
				}
			} //end for i
			if(maxDushu > 0){
				for(int m=0; m<m_numAttr; m++){
					if(maxAttr != m_classIndex && m != m_classIndex && maxAttr != m
							&& !delAttrGroup.get(maxAttr) && !delAttrGroup.get(m)){
						if(maxAttr > m)
							m_corr_matrix[maxAttr][m] = -999;
						else if(maxAttr < m)
							m_corr_matrix[m][maxAttr] = -999;
					}
				}
				delAttrGroup.set(maxAttr);
			}else{
				flag = false;
			}
		} //end while
		if(debug)
			System.out.println("被删除的所有属性："+delAttrGroup);
	}
	
	/**
	 * 获取最终的数据集
	 * @return
	 */
	public Instances getEndInstances(){
		Instances endInstances = m_instances;
		int delNum = 0;
		for(int i=0; i<m_numAttr; i++){
			if(delAttrGroup.get(i)){
				endInstances.deleteAttributeAt(i - delNum);
				delNum++;
			}
		}
		return endInstances;
	}
	
	/**
	 * 获取两个属性之间的相关度
	 * @param i
	 * @param j
	 * @return
	 */
	private double getCorrelation(int i, int j){
		if(i > j)
			return m_corr_matrix[i][j];
		else if(j > i)
			return m_corr_matrix[j][i];
		else
			return 1.0;
	}
	
	//打印相关度矩阵
	private void printCorr(){
		for(int i = 0; i < m_numAttr; i++){
			for(int j =0; j < m_corr_matrix[i].length; j++){
				System.out.print(m_corr_matrix[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag25.arff");
		Instances instances = new Instances( frData );
		instances.setClassIndex( instances.numAttributes()-1 );
		ConnectSubGraph csg = new ConnectSubGraph(0.001, 0.3);
		csg.search(instances);
		System.out.println(csg.getEndInstances());
	}
}

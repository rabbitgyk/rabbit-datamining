package com.rabbit.data.attributeSelection.csg;

import java.io.FileReader;
import java.util.Arrays;
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
	private double[][] corrMatrixBak; //备份原始的相关度矩阵
	
	private boolean debug = false;
	
	/**
	 * 初始化基于极大连通子图的相关度属性选择算法
	 * @param data　数据集
	 * @param threshold0　删除与类属性不相关的属性，相关度阈值
	 * @param threshold　删除冗余的属性，相关度阈值
	 * @throws Exception
	 */
	public ConnectSubGraph(Instances data, double threshold0, double threshold) throws Exception {
		m_instances = new Instances(data);
		m_numAttr = m_instances.numAttributes();
		m_classIndex = m_instances.classIndex();
		MICorrelationEval mic = new MICorrelationEval();
		mic.buildEvaluator(m_instances);
		m_corr_matrix = mic.getMICorrMatrix();
		bakCorrMatrix();
//		defaultThreshold();
		m_threshold0 = threshold0;
		m_threshold = threshold;
	}
	
	/**
	 * 备份原始的相关度矩阵
	 */
	private void bakCorrMatrix(){
		corrMatrixBak = new double[m_corr_matrix.length][];
		for(int i = 0; i < m_corr_matrix.length; i++){
			corrMatrixBak[i] = new double[m_corr_matrix[i].length];
			for(int j =0; j < m_corr_matrix[i].length; j++){
				corrMatrixBak[i][j] = m_corr_matrix[i][j];
			}
		}
	}
	
	/**
	 * 设置默认的阈值
	 */
	private void defaultThreshold(){
		m_threshold0 = 0.001;
		
		double[] corrLine = new double[m_numAttr*(1+m_numAttr)/2];
		int count = 0;
		for(int i=0; i<corrMatrixBak.length; i++){
			for(int j=0; j<corrMatrixBak[i].length; j++){
				corrLine[count] = corrMatrixBak[i][j];
				count++;
			}
		}
		Arrays.sort(corrLine);
		int head = 0;
		int tail = corrLine.length - 1;
		for(int i=0; i<corrLine.length; i++){
			if(corrLine[i] != 0.0){
				head = i;
				break;
			}
		}
		for(int i=0; i<corrLine.length; i++){
			if(corrLine[corrLine.length - i-1] != 1.0){
				tail = corrLine.length - i-1;
				break;
			}
		}
		System.out.println("head and tail corr:");
		for(int i=head; i<=tail; i++){
			System.out.print(" "+corrLine[i]);
		}
		System.out.println();
		while(true){
			double sum = 0.0;
			for(int i=head; i<=tail; i++){
				sum += corrLine[i];
			}
			double mean = sum / (tail -head + 1);
			double var = 0.0;
			for(int j=head; j<=tail; j++){
				var += Math.abs(corrLine[j] -mean);
			}
			var = var / (tail -head + 1);
			System.out.println(String.format("mean:%s, var:%s, count:%s", mean, var, (tail -head + 1)));
			if(var > 0.1){
				if(Math.abs(corrLine[head]-mean) >= Math.abs(corrLine[tail]-mean))
					head++;
				else
					tail--;
			}else{
				m_threshold = mean*2;
				break;
			}
		}
	}
	
	/**
	 * 设置删除不相关属性的阈值
	 * @param alpha
	 */
	public void setClassThreshold(double alpha){
		m_threshold0 = alpha;
	}
	/**
	 * 设置删除冗余属性的阈值
	 * @param beta
	 */
	public void setAttrThreshold(double beta){
		m_threshold = beta;
	}
	/**
	 * 获得所有属性之间的相关度
	 * @return
	 */
	public double[][] getMICorrMatrix(){
		return m_corr_matrix;
	}
	/**
	 * 获得所有属性之间的原始的相关度
	 * @return
	 */
	public double[][] getOriginMICorrMatrix(){
		return corrMatrixBak;
	}

	/**
	 * 根据相关度矩阵选择属性(threshold是相关度阈值)(基于连通图的)
	 * 删除多余的条件属性，使条件属性之间的相关度最低（方法：删除联通子图中度数最大的顶点）
	 * @param data
	 * @throws Exception
	 */
	public void search() throws Exception {
		//重置相关度数组
		for(int i = 0; i < corrMatrixBak.length; i++){
			for(int j =0; j < corrMatrixBak[i].length; j++){
				m_corr_matrix[i][j] = corrMatrixBak[i][j];
			}
		}
		if(debug)
			printCorr();
		if(delAttrGroup == null)
			delAttrGroup = new BitSet(m_numAttr);
		else
			delAttrGroup.clear();
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
		Instances endInstances = new Instances(m_instances);
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
		FileReader frData = new FileReader("/home/rabbit/data/my/CountryFlag28.arff");
		Instances instances = new Instances( frData );
		instances.setClassIndex( instances.numAttributes()-1 );
		System.out.println(instances);
		ConnectSubGraph csg = new ConnectSubGraph(instances, 0.001, 0.1);
		csg.search();
		csg.printCorr();
		System.out.println(csg.getEndInstances());
	}
}

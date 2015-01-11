package com.rabbit.data.attributeSelection.qpfs;

import com.rabbit.data.core.DiscretizeUtil;
import com.rabbit.data.core.EntropyUtil;

import weka.core.Attribute;
import weka.core.Instances;

/**
 * 所有属性（不包括类属性）的相关度矩阵
 * 
 * @author rabbit
 * @date   Jan 1, 2015
 */
public class CondiMIEval {
	
	private double[][] matrix = null; //条件互信息的对称矩阵
	private Instances discData = null; //离散化之后的数据集
	private int numAtt = 0; //属性总个数--包括类属性
	private Attribute classAtt = null; //类属性
	
	public CondiMIEval(Instances data) throws Exception{
		this.discData = DiscretizeUtil.discretize(data);
		this.numAtt = discData.numAttributes();
		this.classAtt = discData.classAttribute();
		
		matrix = new double[numAtt-1][numAtt-1];
	}
	
	/**
	 * 计算出对称矩阵
	 * @throws Exception 
	 */
	public void computeMatrix() throws Exception{
		for(int i=0; i<numAtt-1; i++){
			for(int j=0; j<=i; j++){
				double MIij = EntropyUtil.computeCondiMI(discData, discData.attribute(i), classAtt, discData.attribute(j));
				double MIji = EntropyUtil.computeCondiMI(discData, discData.attribute(j), classAtt, discData.attribute(i));
				matrix[i][j] = (MIij + MIji)/2;
				if(i != j){
					matrix[j][i] = matrix[i][j];
				}
			}
		}
	}
	
	/**
	 * 获取计算好的矩阵
	 * @return
	 */
	public double[][] getMatrix(){
		return matrix;
	}

}

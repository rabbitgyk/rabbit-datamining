package com.rabbit.data.attributeSelection.qpfs;

import weka.core.Instances;
import weka.core.matrix.EigenvalueDecomposition;
import weka.core.matrix.Matrix;

/**
 * 基于Rayleigh熵的属性选择算法，这里处理的矩阵是对称矩阵
 * @author rabbit
 * @date   Jan 1, 2015
 */
public class RayleighFS {

	//原始的数据集
	private Instances data = null;
	
	//矩阵的维数
	private int num = 0;
	
	//特征值是从小到大有序的
	private double[] eigenValues = null;
	//特征向量是和特征值一一对应的
	private Matrix eigenVectors = null;
	
	public RayleighFS(){
		
	}
	
	public RayleighFS(Instances data) throws Exception{
		this.data = data;
		CondiMIEval cme = new CondiMIEval(data);
		cme.computeMatrix();
		double[][] m = cme.getMatrix();
		computeEigen(m);
	}
	
	/**
	 * 返回包含k个属性的Instances
	 * @param k
	 * @return
	 */
	public Instances getEndInstances(int k){
		Instances endInstances = new Instances(data);
		int[] sort = sortAtts();
//		//其他算法调整××××××
//		adjust2(sort);
		
		int[] delPosition = new int[sort.length-k];
		for(int i=k; i<sort.length; i++){
			delPosition[i-k] = sort[i];
//			System.out.println(sort[i]);
		}
		sort(delPosition);
		
		int delNum = 0;
		for(int i=0; i<delPosition.length; i++){
			endInstances.deleteAttributeAt(delPosition[i]-delNum);
			delNum++;
		}
		
		return endInstances;
	}
	
	//调整1-----EQPFS
	private void adjust1(int[] sort){
		int len = sort.length;
		String[] adNum = {"2 6","3 14","11 13","15 28","22 24","26 29",
				"31 32","12 35","37 39","38 40"};
		for(int i=0; i<adNum.length; i++){
			String[] strs = adNum[i].split(" ");
			int num1 = Integer.parseInt(strs[0]);
			int num2 = Integer.parseInt(strs[1]);
			if(num1<len && num2<len){
				int tem = sort[num1];
				sort[num1] = sort[num2];
				sort[num2] = tem;
			}
		}
	}
	
	//调整2-----QPFS
	private void adjust2(int[] sort){
		int len = sort.length;
		String[] adNum = {"2 6","3 14","10 13","15 28","22 24","16 29",
				"31 32","12 35","21 39","38 40"};
		for(int i=0; i<adNum.length; i++){
			String[] strs = adNum[i].split(" ");
			int num1 = Integer.parseInt(strs[0]);
			int num2 = Integer.parseInt(strs[1]);
			if(num1<len && num2<len){
				int tem = sort[num1];
				sort[num1] = sort[num2];
				sort[num2] = tem;
			}
		}
	}
	
	/**
	 * 计算矩阵matrix的特征值和特征向量
	 * 
	 * @param matrix
	 */
	public void computeEigen(double[][] mat){
		num = mat.length;
		Matrix matrix = new Matrix(mat);
		EigenvalueDecomposition ed = matrix.eig();
		eigenVectors = ed.getV();
		eigenValues = ed.getRealEigenvalues();
	}
	
	/**
	 * 根据主特征向量，将所有的属性排序
	 * @return
	 */
	public int[] sortAtts(){
		//保存顺序
		int[] orders = new int[num];
		for(int i=0; i<num; i++){
			orders[i] = i;
		}
		//主特征向量
		double[] mainVec = mainEigenVector();
		sort(mainVec, orders);
		return orders;
	}
	
//	public static void main(String[] args) {
//		double[] data = {0.3, 0.2, 0.4, 0.9, 0.7, 0.1};
//		int[] orders = {6,1,2,3,4,5};
//		
//		sort(orders);
//		for(int i=0;i<data.length; i++){
//			System.out.println(data[i]+" "+orders[i]+", ");
//		}
//	}
	
	/**
	 * 排序data数组并保存原来的位置,从大到小
	 * @param data
	 * @param orders
	 */
	private void sort(double[] data, int[] orders){
		for(int i=1; i<data.length; i++){
			if(data[i-1] < data[i]){
				double tem = data[i];
				int order = orders[i];
				int j = i;
				while(j > 0 && data[j-1] < tem){
					data[j] = data[j-1];
					orders[j] = orders[j-1];
					j--;
				}
				data[j] = tem;
				orders[j] = order;
			}
		}
	}
	
	/**
	 * 插入排序数组data，从小到大
	 * @param data
	 */
	private void sort(int[] data){
		for(int i=1; i<data.length; i++){
			if(data[i-1] > data[i]){
				int tem = data[i];
				int j = i;
				while(j > 0 && data[j-1] > tem){
					data[j] = data[j-1];
					j--;
				}
				data[j] = tem;
			}
		}
	}
	
	/**
	 * 获得主特征值
	 * @return
	 */
	public double mainEigenValue() {
		return eigenValues[num-1];
	}
	
	/**
	 * 获取主特征向量
	 * @return
	 */
	public double[] mainEigenVector() {
		double[] vector = new double[num];
		for(int i=0; i<num; i++){
			vector[i] = eigenVectors.get(i, num-1);
		}
		return vector;
	}

	/**
	 * 获取所有的特征值
	 * @return
	 */
	public double[] getEigenValues() {
		return eigenValues;
	}

	/**
	 * 获取所有的特征向量
	 * @return
	 */
	public Matrix getEigenVectors() {
		return eigenVectors;
	}
	
	
	
}

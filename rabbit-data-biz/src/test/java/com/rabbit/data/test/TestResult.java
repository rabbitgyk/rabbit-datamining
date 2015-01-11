package com.rabbit.data.test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import weka.core.Instances;

import com.rabbit.data.attributeSelection.ConnectSubGraphImpl;
import com.rabbit.data.attributeSelection.SampleOneImpl;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

public class TestResult {
	
	public static void main(String[] args) throws Exception{
		
		String saveFile = "/home/rabbit/data/my/result.txt";
		
		//数据集数组
		String[] datas = {"ionosphere.arff","soybean.arff","my/waveform21.arff","my/waveform40.arff",
				"my/wdbc31.arff","my/CountryFlag28.arff"};
		
		for(int i=0; i<datas.length; i++){
			FileReader frData = new FileReader("/home/rabbit/data/"+datas[i]);
			Instances data = new Instances(frData);
			data.setClassIndex(data.numAttributes() - 1);
			
			String result = eval(data);
			writeFile(result, saveFile);
		}
		
	}
	
	
	/**
	 * 得到数据集data的各项分类精度，属性选择前后
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String eval(Instances data) throws Exception {
		
		System.out.println("开始计算数据集: " + data.relationName());
		
		StringBuilder sb = new StringBuilder();
		sb.append("\nDataset name: " + data.relationName());
		sb.append("\nAttribute number: " + data.numAttributes());
		
		//-------------------原始数据集-----------------------------------------
		System.out.println("正在计算--------原始数据集-----------");
		sb.append("\n\n-------------------原始数据集-----------------------------");
		NaiveBayesImpl nbi = new NaiveBayesImpl(data);
		nbi.runClassifier();
		sb.append("\nNaiveBayesImpl:" + nbi.pctCorrect());
		
		J48Impl j48i = new J48Impl(data);
		j48i.runClassifier();
		sb.append("\nJ48Impl:" + j48i.pctCorrect());
		
		//-------------------极大连通子图----------------------------------------
		System.out.println("正在计算--------极大连通子图-----------");
		sb.append("\n\n-------------------极大连通子图--------------------------");
		ConnectSubGraphImpl csgi = new ConnectSubGraphImpl(data);
		NaiveBayesImpl nbi_csg = new NaiveBayesImpl();
		csgi.setEvalClassifier(nbi_csg);
		csgi.buildAS();
		Instances csg_nbi_data = csgi.endInstances();
		sb.append("\nMaxBeta: "+csgi.getMaxBeta());
		sb.append("\nConnectSubGraphImpl attrs:"+csg_nbi_data.numAttributes());
		nbi_csg.setData(csg_nbi_data);
		nbi_csg.runClassifier();
		sb.append("\nNaiveBayesImpl: " + nbi_csg.pctCorrect());
		
		ConnectSubGraphImpl csgi1 = new ConnectSubGraphImpl(data);
		J48Impl j48i_csg = new J48Impl();
		csgi1.setEvalClassifier(j48i_csg);
		csgi1.buildAS();
		Instances csg_j48i_data = csgi1.endInstances();
		sb.append("\nMaxBeta: "+csgi1.getMaxBeta());
		sb.append("\nConnectSubGraphImpl attrs:"+csg_j48i_data.numAttributes());
		j48i_csg.setData(csg_j48i_data);
		j48i_csg.runClassifier();
		sb.append("\nJ48Impl:" + j48i_csg.pctCorrect());
		
		//-----------------------简单相关度属性选择算法---------------------------------------------------
		System.out.println("正在计算--------简单相关度属性选择算法-----------");
		sb.append("\n\n---------------简单相关度属性选择算法--------------------");
		SampleOneImpl soi = new SampleOneImpl(data);
		NaiveBayesImpl nbi_soi = new NaiveBayesImpl();
		soi.setEvalClassifier(nbi_soi);
		soi.buildAS();
		Instances soi_nbi_data = soi.endInstances();
		sb.append("\nMaxBeta: "+soi.getMaxBeta());
		sb.append("\nSampleOneImpl attrs:"+soi_nbi_data.numAttributes());
		nbi_soi.setData(soi_nbi_data);
		nbi_soi.runClassifier();
		sb.append("\nNaiveBayesImpl:" + nbi_soi.pctCorrect());
		
		SampleOneImpl soi1 = new SampleOneImpl(data);
		J48Impl j48i_soi = new J48Impl();
		soi1.setEvalClassifier(j48i_soi);
		soi1.buildAS();
		Instances soi_j48i_data = soi1.endInstances();
		sb.append("\nMaxBeta: "+soi1.getMaxBeta());
		sb.append("\nSampleOneImpl attrs:"+soi_j48i_data.numAttributes());
		j48i_soi.setData(soi_j48i_data);
		j48i_soi.runClassifier();
		sb.append("\nJ48Impl:" + j48i_soi.pctCorrect());
		
		System.out.println("结束计算数据集: " + data.relationName());
		return sb.toString();
	}
	
	/**
	 * 把con追加到文件filename
	 * @param con
	 * @param fileName
	 */
	public static void writeFile(String con, String fileName){
        FileOutputStream fos=null;
        BufferedWriter bw=null;
        try {
            fos=new FileOutputStream(fileName, true); // 追加
            bw=new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            bw.write(con+"\r\n\r\n\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                bw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

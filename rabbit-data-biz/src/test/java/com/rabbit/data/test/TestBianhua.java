package com.rabbit.data.test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

import com.rabbit.data.attributeSelection.csg.ConnectSubGraphFS;
import com.rabbit.data.attributeSelection.csg.SampleOneFS;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

import weka.core.Instances;

public class TestBianhua {
	
	public static void main(String[] args) throws Exception {
		String saveFile = "/home/rabbit/data/my/result_sofs_j48.txt";
		
		//数据集数组
		String[] datas = {"ionosphere.arff","my/waveform21.arff","my/waveform40.arff",
				"my/wdbc31.arff"};
		
		for(int i=0; i<datas.length; i++){
			FileReader frData = new FileReader("/home/rabbit/data/"+datas[i]);
			Instances data = new Instances(frData);
			data.setClassIndex(data.numAttributes() - 1);
			
			String result = eval(data);
			writeFile(result, saveFile);
		}
		
//		FileReader frData = new FileReader("/home/rabbit/data/my/waveform21.arff");
//		Instances data = new Instances(frData);
//		data.setClassIndex(data.numAttributes() - 1);
//		
//		String result = eval(data);
//		System.out.println(result);
	}
	
	public static String eval(Instances data) throws Exception{
		StringBuilder sb = new StringBuilder();
//		sb.append("NaiveBayesImpl\n");
		sb.append("J48Impl\n");
		sb.append("SampleOneFS\n");
//		sb.append("ConnectSubGraphFS\n");
		sb.append(data.relationName());
		sb.append("\n");
		double alpha0 = 0.001;
		double beta0 = 0.01;
//		NaiveBayesImpl classifier = new NaiveBayesImpl();
		J48Impl classifier = new J48Impl();
		
		SampleOneFS so = new SampleOneFS(data, alpha0, beta0);
//		ConnectSubGraphFS so = new ConnectSubGraphFS(data, alpha0, beta0);
		
		while(beta0 <= 1.0){
			so.setAttrThreshold(beta0);
			so.search();
			Instances end = so.getEndInstances();
			classifier.setData(end);
			classifier.runClassifier();
			sb.append(beta0);
			sb.append("\t");
			sb.append(end.numAttributes()-1);
			sb.append("\t");
			sb.append(classifier.pctCorrect());
			sb.append("\n");
			if(beta0 == 0.01){
				beta0 = beta0+0.04;
			}else{
				beta0 = beta0+0.05;
			}
			DecimalFormat df = new DecimalFormat("#.00");
			beta0 = Double.parseDouble(df.format(beta0));
		}
		sb.append("\r\n\r\n");
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

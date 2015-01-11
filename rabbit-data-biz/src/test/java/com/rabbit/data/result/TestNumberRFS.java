package com.rabbit.data.result;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.rabbit.data.attributeSelection.qpfs.RayleighFS;
import com.rabbit.data.classifier.J48Impl;
import com.rabbit.data.classifier.NaiveBayesImpl;

import weka.core.Instances;

/**
 * 基于二次规划的属性选择算法QPFS的最优属性子集中的属性个数和对应分类准确率的数据记录
 * 
 * @author rabbit
 * @date   Jan 11, 2015
 */
public class TestNumberRFS {

	public static void main(String[] args) throws Exception{
		
		String saveFile = "/home/rabbit/data/my/result_QPFS.txt";
		
		//数据集数组
		String[] datas = {"ionosphere.arff","my/waveform21.arff","my/waveform40.arff",
				"my/wdbc31.arff","my/CountryFlag28.arff"};
		
		for(int i=0; i<datas.length; i++){
			FileReader frData = new FileReader("/home/rabbit/data/"+datas[i]);
			Instances data = new Instances(frData);
			data.setClassIndex(data.numAttributes() - 1);
			
			String result = eval(data);
			writeFile(result, saveFile);
		}
		
//		FileReader frData = new FileReader("/home/rabbit/data/my/waveform40.arff");
//		Instances data = new Instances(frData);
//		data.setClassIndex(data.numAttributes() - 1);
//		
//		String result = eval(data);
//		System.out.println(result);
		
	}
	
	
	public static String eval(Instances data) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(data.relationName());
		sb.append("\n");
		RayleighFS rfs = new RayleighFS(data);
		int numAtt = data.numAttributes();
		
		NaiveBayesImpl classifier = new NaiveBayesImpl();
		sb.append("NaiveBayesImpl");
		sb.append("\n");
		for(int i=1; i<numAtt; i++){
			Instances end = rfs.getEndInstances(i);
			classifier.setData(end);
			classifier.runClassifier();
			sb.append(i);
			sb.append("\t");
			sb.append(classifier.pctCorrect());
			sb.append("\n");
		}
		
		J48Impl classifier1 = new J48Impl();
		sb.append("J48Impl");
		sb.append("\n");
		for(int i=1; i<numAtt; i++){
			Instances end = rfs.getEndInstances(i);
			classifier1.setData(end);
			classifier1.runClassifier();
			sb.append(i);
			sb.append("\t");
			sb.append(classifier1.pctCorrect());
			sb.append("\n");
		}
		sb.append("\n");
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

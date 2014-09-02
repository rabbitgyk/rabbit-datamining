package com.rabbit.think.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileReadWrite {

	public static void main(String[] args) {
		
		String fileName = "/home/ykguo/data/basic.txt";
		readAndWrite(fileName, 1000);
	}
	
	public static void readAndWrite(String fileName, int lines){
		FileInputStream fis = null;
        BufferedReader br=null;
        try {
            fis=new FileInputStream(fileName);
            br=new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            
            String[] con=new String[lines];
            int i = 0;
            String writeFile = "/home/ykguo/data/basic_split/basic_split";
            String content=null;
            while((content=br.readLine())!=null){
            	con[i % lines] = content;
            	i++;
            	if(i % lines == 0){
            		writeFile(con, con.length, writeFile + (i / (lines * 10)));
            	}
            }
            writeFile(con, i % lines, writeFile + (i / (lines * 10)));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	/**
	 * 将数组con中的数据写到文件fileName中(每个元素一行)
	 * @param con
	 * @param fileName
	 */
	public static void writeFile(String[] con, int length, String fileName){
		FileOutputStream fos=null;
        BufferedWriter bw=null;
        try {
            fos=new FileOutputStream(fileName, true); // 追加
            bw=new BufferedWriter(new OutputStreamWriter(fos, "UTF-8"));
            for(int i=0;i<length;i++)
            {
                String str=con[i];
                bw.write(str+"\r\n");
            }
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

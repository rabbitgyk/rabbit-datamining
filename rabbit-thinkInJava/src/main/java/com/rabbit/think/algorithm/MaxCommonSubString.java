package com.rabbit.think.algorithm;
/**
 * 考虑对上述算法优化，可以将两个字符串每个位置上的字符的比较结果保存到一张二维表中，
 * 这张表中的[i,j]位置就表示第一个字符串的第i个字符与第二个字符串的第j个字符的比较结果，
 * 1表示字符相同，0表示字符不相同。在匹配最长子串的过程中，不必多次重复判断两个字符是否相等，
 * 只需从表中的[i,j]位置直接得到结果即可。
 * @author rabbit
 * @date   Aug 30, 2014
 */
public class MaxCommonSubString {
	/**
	 * 待比较的两个字符串
	 */
	private String s1;
	private String s2;
	/**
	 * 保存比较结果的二维表
	 */
	private int[][] indexs;
	
	public MaxCommonSubString(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		indexs = new int[s1.length()][s2.length()];
	}
	
	/**
	 * 生成一张二维表，保存比较结果(1表示字符相同，0表示字符不相同)
	 */
	public void initIndexs_raw(){
		char[] a = s1.toCharArray();
		char[] b = s2.toCharArray();
		for(int i=0; i<a.length; i++){
			for(int j=0; j<b.length; j++){
				if(a[i] == b[j])
					indexs[i][j] = 1;
				System.out.print(indexs[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * 改进版indexs
	 * 生成一张二维表，保存比较结果(大于0的数字表示该位置之前字符相同个数，0表示字符不相同)
	 */
	public void initIndexs(){
		char[] a = s1.toCharArray();
		char[] b = s2.toCharArray();
		for(int i=0; i<a.length; i++){
			for(int j=0; j<b.length; j++){
				if(a[i] == b[j]){
					int m = i-1;
					int n = j-1;
					if(m >=0 && n>=0)
						indexs[i][j] = indexs[m][n] + 1;
					else
						indexs[i][j] = 1;
				}
//				System.out.print(indexs[i][j]);
			}
//			System.out.println();
		}
	}
	
	/**
	 * 获得最长的公共子串(没考虑多个相同最长,取第一个最长)
	 * @return
	 */
	public String getMaxSubString(){
		char[] a = s1.toCharArray();
		int maxLen = 0;
		int lastI = 0;
		int lastJ = 0;
		for(int i=0; i<indexs.length; i++){
			for(int j=0; j<indexs[i].length; j++){
				if(indexs[i][j] > maxLen){
					maxLen = indexs[i][j];
					lastI = i;
					lastJ = j;
				}
			}
		}
		
		StringBuffer sb = new StringBuffer(maxLen);
		for(int i=(lastI-maxLen+1); i<=lastI; i++){
			sb.append(a[i]);
		}
		return sb.toString();
	}
	
	public int[][] getIndexs() {
		return indexs;
	}

	public static void main(String[] args) {
		String s1 = "abcdefcdefdef";
		String s2 = "amcdeffdabc";
		MaxCommonSubString mcs = new MaxCommonSubString(s1, s2);
		mcs.initIndexs();
		System.out.println("------------------------------------------------------");
		System.out.println(mcs.getMaxSubString());
	}

}

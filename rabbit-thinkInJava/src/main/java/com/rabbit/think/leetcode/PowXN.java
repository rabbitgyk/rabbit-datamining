package com.rabbit.think.leetcode;

/**
 * Implement pow(x, n).
 * 
 * @author rabbit
 * @date   Oct 5, 2014
 */
public class PowXN {

	public double pow(double x, int n) {
        if(n < 0 && x == 0.0){
            return 0.0;
        }
        if(n < 0){
            n = -n;
            x = 1.0/x;
        }
        return absPow(x, n);
    }
    
    //正整数次幂
    public double absPow(double x, int n){
        double[] xPow = new double[32];
        xPow[0] = x;
        for(int i=1; i<32; i++){
            xPow[i] = xPow[i-1] * xPow[i-1];
        }
        double result = 1.0;
        for(int i=0; i<32; i++){
            if((n & (0x1 << i)) != 0){
                result = result * xPow[i];
            }
        }
        return result;
    }
}

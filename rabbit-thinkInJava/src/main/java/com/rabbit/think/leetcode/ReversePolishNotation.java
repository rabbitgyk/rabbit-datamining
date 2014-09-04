package com.rabbit.think.leetcode;

import java.util.Stack;
/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * 
 * @author rabbit
 * @date   Sep 4, 2014
 */
public class ReversePolishNotation {
	
	public static void main(String[] args) {
		String[] tokens = {"2", "1", "+", "3", "*"};
		System.out.println(evalRPN(tokens));
	}
	
	/**
	 * 栈来解决后缀的算术表达式
	 * @param tokens
	 * @return
	 */
	public static int evalRPN(String[] tokens){
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<tokens.length; i++){
			String token = tokens[i];
			if(isNum(token)){
				stack.push(Integer.parseInt(token));
			}else if(token.equals("+")){
				int num1 = stack.pop();
				int num2 = stack.pop();
				int res = num2 + num1;
				stack.push(res);
			}else if(token.equals("-")){
				int num1 = stack.pop();
				int num2 = stack.pop();
				int res = num2 - num1;
				stack.push(res);
			}else if(token.equals("*")){
				int num1 = stack.pop();
				int num2 = stack.pop();
				int res = num2 * num1;
				stack.push(res);
			}else if(token.equals("/")){
				int num1 = stack.pop();
				int num2 = stack.pop();
				int res = num2 / num1;
				stack.push(res);
			}
		}
		
		return stack.pop();
	}
	public static boolean isNum(String str){
		return str.matches("^-?[0-9]\\d*$");
	}

}

package com.rabbit.vip.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DecimalUtil {
	private static final int DEFAULT_PRECISION = 10;
	private static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private static final MathContext DEFULAT_MATH_CONTEXT = new MathContext(DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE);

	public static BigDecimal multiply(Number number1, Number number2) {
		BigDecimal number1Big = new BigDecimal(number1.toString());
		BigDecimal number2Big = new BigDecimal(number2.toString());
		return number1Big.multiply(number2Big, DEFULAT_MATH_CONTEXT);
	}

	public static BigDecimal add(Number number1, Number number2) {
		BigDecimal number1Big = new BigDecimal(number1.toString());
		BigDecimal number2Big = new BigDecimal(number2.toString());
		return number1Big.add(number2Big, DEFULAT_MATH_CONTEXT);
	}

	public static BigDecimal sub(Number number1, Number number2) {
		BigDecimal number1Big = new BigDecimal(number1.toString());
		BigDecimal number2Big = new BigDecimal(number2.toString());
		return number1Big.multiply(number2Big, DEFULAT_MATH_CONTEXT);
	}

	public static BigDecimal divide(Number number1, Number number2) {
		BigDecimal number1Big = new BigDecimal(number1.toString());
		BigDecimal number2Big = new BigDecimal(number2.toString());
		return number1Big.divide(number2Big, DEFULAT_MATH_CONTEXT);
	}
}

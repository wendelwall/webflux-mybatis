package com.wendel.utils;

/***
 * Base62编码
 * @author admin
 *
 */
public class Base62 {
	
	private static final String baseDigits = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int BASE = baseDigits.length();
	private static final char[] digitsChar = baseDigits.toCharArray();
	private static final int FAST_SIZE = 'z';
	private static final int[] digitsIndex = new int[FAST_SIZE + 1];

	static {
		for (int i = 0; i < FAST_SIZE; i++) {
			digitsIndex[i] = -1;
		}
		for (int i = 0; i < BASE; i++) {
			digitsIndex[digitsChar[i]] = i;
		}
	}
	/***
	 * 私有构造方法
	 */
	private Base62(){
	}
	
	/**
	 * Base62编码
	 * @param number 输入参数
	 * @return Base62编码后字符串
	 */
	public static String encode(long number) {
		if (number < 0)
			throw new IllegalArgumentException("Number(Base62) must be positive: " + number);
		if (number == 0)
			return "0";
		StringBuilder buf = new StringBuilder();
		while (number != 0) {
			buf.append(digitsChar[(int) (number % BASE)]);
			number /= BASE;
		}
		return buf.reverse().toString();
	}
	/***
	 * Base62解码
	 * @param source 输入码值
	 * @return 返回解码后结果
	 */
	public static long decode(String source) {
		long result = 0L;
		long multiplier = 1;
		for (int pos = source.length() - 1; pos >= 0; pos--) {
			int index = getIndex(source, pos);
			result += index * multiplier;
			multiplier *= BASE;
		}
		return result;
	}
	/***
	 * 私有方法
	 * @param source
	 * @param pos
	 * @return
	 */
	private static int getIndex(String source, int pos) {
		char c = source.charAt(pos);
		if (c > FAST_SIZE) {
			throw new IllegalArgumentException("Unknow character for Base62: " + source);
		}
		int index = digitsIndex[c];
		if (index == -1) {
			throw new IllegalArgumentException("Unknow character for Base62: " +source);
		}
		return index;
	}
}

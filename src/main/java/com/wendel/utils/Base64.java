package com.wendel.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/***
 * Base64 编码转换工具
 * @author admin
 *
 */
public class Base64 {

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	final static Map<Character, Integer> digitMap = new HashMap<Character, Integer>();

	static {
		for (int i = 0; i < digits.length; i++) {
			digitMap.put(digits[i], (int) i);
		}
	}

	/**
	 * 支持的最大进制数
	 */
	public static final int MAX_RADIX = digits.length;

	/**
	 * 支持的最小进制数
	 */
	public static final int MIN_RADIX = 2;

	/**
	 * 私有构造方法
	 */
	private Base64() {
	}

	/**
	 * Base64 转码：将长整型数值转换为指定的进制数（最大支持62进制，字母数字已经用尽） 
	 * @param source 数值源
	 * @param radix 进制数：2~62
	 * @return 返回转换后结果
	 */
	public static String encode(long source, int radix) {
		if (radix < MIN_RADIX || radix > MAX_RADIX)
			radix = 10;
		if (radix == 10)
			return Long.toString(source);

		final int size = 65;
		int charPos = 64;

		char[] buf = new char[size];
		boolean negative = (source < 0);

		if (!negative) {
			source = -source;
		}

		while (source <= -radix) {
			buf[charPos--] = digits[(int) (-(source % radix))];
			source = source / radix;
		}
		buf[charPos] = digits[(int) (-source)];

		if (negative) {
			buf[--charPos] = '-';
		}

		return new String(buf, charPos, (size - charPos));
	}

	/***
	 * Base64解码：将Base64字符串转换为长整型数字
	 * @param source Base64字符串
	 * @param radix 进制数：2~62
	 * @return 返回转换后结果
	 */
	public static long decode(String source, int radix) {
		if (source == null) {
			throw new NumberFormatException("null");
		}

		if (radix < MIN_RADIX) {
			throw new NumberFormatException("radix " + radix + " less than Numbers.MIN_RADIX");
		}
		if (radix > MAX_RADIX) {
			throw new NumberFormatException("radix " + radix + " greater than Numbers.MAX_RADIX");
		}

		long result = 0;
		boolean negative = false;
		int i = 0, len = source.length();
		long limit = -Long.MAX_VALUE;
		long multmin;
		Integer digit;
		if (len > 0) {
			char firstChar = source.charAt(0);
			if (firstChar < '0') {
				if (firstChar == '-') {
					negative = true;
					limit = Long.MIN_VALUE;
				} else if (firstChar != '+')
					throw forInputString(source);

				if (len == 1) {
					throw forInputString(source);
				}
				i++;
			}
			multmin = limit / radix;
			while (i < len) {
				digit = digitMap.get(source.charAt(i++));
				if (digit == null) {
					throw forInputString(source);
				}
				if (digit < 0) {
					throw forInputString(source);
				}
				if (result < multmin) {
					throw forInputString(source);
				}
				result *= radix;
				if (result < limit + digit) {
					throw forInputString(source);
				}
				result -= digit;
			}
		} else {
			throw forInputString(source);
		}
		return negative ? result : -result;
	}
	/***
	 * 生成Base64的UUID,19位数
	 * @return 19位数UUID
	 */
	public static String base64Uuid() {
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
		sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
		sb.append(digits(uuid.getMostSignificantBits(), 4));
		sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
		sb.append(digits(uuid.getLeastSignificantBits(), 12));
		return sb.toString();
	}
	/***
	 * 私有方法
	 * @param source 源数据
	 * @return 封装异常类
	 */
	private static NumberFormatException forInputString(String source) {
		return new NumberFormatException("For input string: \"" + source + "\"");
	}

	/***
	 * 私有方法
	 * @param val
	 * @param digits
	 * @return 返回结果
	 */
	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Base64.encode(hi | (val & (hi - 1)), Base64.MAX_RADIX).substring(1);
	}
}
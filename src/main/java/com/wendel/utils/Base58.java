package com.wendel.utils;


import org.apache.commons.lang3.StringUtils;

/***
 * Base58编码转换工具
 * @author admin
 *
 */
public class Base58 {
	 
	private static final char[] ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
	
	private static final int BASE_58 = ALPHABET.length;
	
	private static final int BASE_256 = 256;
 
	private static final int[] INDEXES = new int[128];
	
	static {
		for (int i = 0; i < INDEXES.length; i++) {
			INDEXES[i] = -1;
		}
		for (int i = 0; i < ALPHABET.length; i++) {
			INDEXES[ALPHABET[i]] = i;
		}
	}
	/***
	 * 私有构造方面
	 */
	private Base58(){}
	/***
	 * Base58 转码:将byte数组转换成Base58码值字符串
	 * @param source 需转码的byte数组
	 * @return 转码后的Base58字符串
	 */
	public static String encode(byte[] source) {
		if (source.length == 0) {
			return "";
		}
		source = copyOfRange(source, 0, source.length);
		int zeroCount = 0;
		while (zeroCount < source.length && source[zeroCount] == 0) {
			++zeroCount;
		}
		byte[] temp = new byte[source.length * 2];
		int j = temp.length;
 
		int startAt = zeroCount;
		while (startAt < source.length) {
			byte mod = divmod58(source, startAt);
			if (source[startAt] == 0) {
				++startAt;
			}
			temp[--j] = (byte) ALPHABET[mod];
		}
		while (j < temp.length && temp[j] == ALPHABET[0]) {
			++j;
		}
		while (--zeroCount >= 0) {
			temp[--j] = (byte) ALPHABET[0];
		}
 
		byte[] output = copyOfRange(temp, j, temp.length);
		return new String(output);
	}
	/**
	 * Base58 转码:字符串转换成Base58码值字符串
	 * @param sourceStr 需转码的字符串
	 * @return 转码后的Base58字符串
	 */
	public static String encode(String sourceStr) {
		if(StringUtils.isEmpty(sourceStr)){
			return null;
		}
		return encode(sourceStr.getBytes());
	}
	/***
	 * Base58 解码:将Base58解码转换成字符串
	 * @param sourceStr 需转码的字符串
	 * @return 解码后的字符串
	 */
	public static String decodeToStr(String sourceStr) {
		if(StringUtils.isEmpty(sourceStr)){
			return null;
		}
		return new String(decode(sourceStr));
	}
	/***
	 * Base58 解码:将Base58解码转换成byte[]数组
	 * @param  sourceStr 转码后的Base58字符串
	 * @return 解码后的byte数组
	 */
	public static byte[] decode(String sourceStr) {
		if (sourceStr.length() == 0) {
			return new byte[0];
		}
		byte[] input58 = new byte[sourceStr.length()];
		for (int i = 0; i < sourceStr.length(); ++i) {
			char c = sourceStr.charAt(i);
 
			int digit58 = -1;
			if (c >= 0 && c < 128) {
				digit58 = INDEXES[c];
			}
			if (digit58 < 0) {
				throw new RuntimeException("Not a Base58 input: " + sourceStr);
			}
			input58[i] = (byte) digit58;
		}
		int zeroCount = 0;
		while (zeroCount < input58.length && input58[zeroCount] == 0) {
			++zeroCount;
		}
		byte[] temp = new byte[sourceStr.length()];
		int j = temp.length;
 
		int startAt = zeroCount;
		while (startAt < input58.length) {
			byte mod = divmod256(input58, startAt);
			if (input58[startAt] == 0) {
				++startAt;
			}
 
			temp[--j] = mod;
		}
		while (j < temp.length && temp[j] == 0) {
			++j;
		}
 
		return copyOfRange(temp, j - zeroCount, temp.length);
	}
	/***
	 * 私有方法
	 * @param number
	 * @param startAt
	 * @return 
	 */
	private static byte divmod58(byte[] number, int startAt) {
		int remainder = 0;
		for (int i = startAt; i < number.length; i++) {
			int digit256 = (int) number[i] & 0xFF;
			int temp = remainder * BASE_256 + digit256;
			number[i] = (byte) (temp / BASE_58);
			remainder = temp % BASE_58;
		}
 
		return (byte) remainder;
	}
	/**
	 * 私有方法
	 * @param number58
	 * @param startAt
	 * @return
	 */
	private static byte divmod256(byte[] number58, int startAt) {
		int remainder = 0;
		for (int i = startAt; i < number58.length; i++) {
			int digit58 = (int) number58[i] & 0xFF;
			int temp = remainder * BASE_58 + digit58;
			number58[i] = (byte) (temp / BASE_256);
			remainder = temp % BASE_256;
		}
 
		return (byte) remainder;
	}
	/***
	 * 私有方法
	 * @param source
	 * @param from
	 * @param to
	 * @return
	 */
	private static byte[] copyOfRange(byte[] source, int from, int to) {
		byte[] range = new byte[to - from];
		System.arraycopy(source, from, range, 0, range.length);
		return range;
	}
}
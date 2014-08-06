package com.rabbit.vip.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	private static Map<Character, String> characterMap = new HashMap<Character, String>();
	private static Map<String, Character> strCharacterMap = new HashMap<String, Character>();
	private static Set<Character> characterSet;
	// http://natural-innovations.com/wa/doc-charset.html 特殊字符转义在这里查
	static {
		characterMap.put('<', "&lt;");
		characterMap.put('>', "&gt;");
		characterMap.put('&', "&amp;");
		characterMap.put('©', "&copy;");
		characterMap.put('®', "&reg;");
		characterMap.put('"', "&quot;");
		characterMap.put('×', "&times;");
		characterMap.put('÷', "&divide;");
		characterMap.put('\\', "&#92;");
		characterMap.put('\'', "&#39;");
		characterMap.put('”', "&rdquo;");
		characterMap.put('“', "&ldquo;");
		characterMap.put('—', "&mdash;");
		characterMap.put('’', "&rsquo;");
		characterMap.put('…', "&hellip;");
		characterMap.put('·', "&middot;");
		characterMap.put(' ', "&nbsp;");
		characterMap.put('¯', "&macr;");
		characterMap.put('´', "&acute;");
		characterMap.put('«', "&laquo;");
		characterMap.put('»', "&raquo;");
		characterMap.put('，', "&lsquor;");
		characterMap.put('é', "&eacute;");

		characterSet = characterMap.keySet();
		for (Entry<Character, String> entry : characterMap.entrySet()) {
			strCharacterMap.put(entry.getValue(), entry.getKey());
		}
	}

	// &amp; selena gomez &amp; the scene&amp; -->>& selena gomez & the scene&
	public static String html2Original(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		StringBuffer result = new StringBuffer();
		String tmp = "";
		boolean record = false;
		for (char c : str.toCharArray()) {
			if (c == '&') {
				tmp += c;
				record = true;
			} else if (c == ';') {
				tmp += c;
				if (strCharacterMap.keySet().contains(tmp)) {
					result.append(strCharacterMap.get(tmp));
				} else {
					result.append(tmp);
				}
				record = false;
				tmp = "";
			} else {
				if (!record) {
					result.append(c);
				} else {
					tmp += c;
				}
			}
		}
		result.append(tmp);

		return result.toString();
	}

	/**
	 * 转全角
	 * 
	 * @param input
	 * @return
	 */
	public static String toSBC(String input) {
		// 半角转全角：
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 32) {
				c[i] = (char) 12288;
				continue;
			}
			if (c[i] < 127)
				c[i] = (char) (c[i] + 65248);
		}
		return new String(c);
	}

	/**
	 * 转半角的函数
	 * 
	 * @param input
	 * @return
	 */
	public static String toDBC(String input) {
		char[] c = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 12288) {
				c[i] = (char) 32;
				continue;
			}
			if (c[i] > 65280 && c[i] < 65375)
				c[i] = (char) (c[i] - 65248);
		}
		return new String(c);
	}

	/**
	 * Url解码
	 * 
	 * @param input
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String decodeUrl(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		input = URLDecoder.decode(input);
		return input;
	}

	/**
	 * Url编码
	 * 
	 * @param input
	 * @return
	 */
	public static String encodeUrl(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		try {
			input = URLEncoder.encode(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		return input;
	}

	/**
	 * 开头结尾的空格回车换行去掉
	 * 
	 * @param input
	 * @return
	 */
	public static String cutHeadTailSpace(String input) {

		if (StringUtils.isBlank(input)) {
			return input;
		}
		char[] cs = input.toCharArray();
		for (int index = 0; index < cs.length; index++) {
			char c = cs[index];
			if (c == '\r' || c == '\n' || c == ' ' || c == 160) {

			} else {
				input = input.substring(index, input.length());
				break;
			}
		}

		if (StringUtils.isBlank(input)) {
			return input;
		}
		cs = input.toCharArray();
		for (int index = cs.length - 1; index >= 0; index--) {
			char c = cs[index];
			if (c == '\r' || c == '\n' || c == ' ' || c == 160) {

			} else {
				input = input.substring(0, index + 1);
				break;
			}
		}
		return input;
	}

	public static String specialHtmlCharacterTrans(String input) {
		StringBuilder result = null;
		if (input != null) {
			result = new StringBuilder();
			char[] chars = input.toCharArray();
			for (char c : chars) {
				if (characterSet.contains(c)) {
					result.append(characterMap.get(c));
				} else {
					result.append(c);
				}
			}
		}
		return (result != null) ? result.toString() : null;
	}

	/** 富媒体计算长度，这里用个简单方法,先把目标替换成相应的长度，然后在替换 **/
	static enum TextRich {
		TEXT_IMG_REGEX("<img.*?class=\"customize-image\".*?/>",
				"替代替代替代替代替代替代替代替"), // NL
									// 15
									// 汉字
		TEXT_SONG_REGEX("<img.*?class=\"customize-song\".*?/>",
				"替代替代替代替代替代替代替代替"), // NL
									// 15
									// 汉字
		TEXT_VIDEO_REGEX("<img.*?class=\"customize-media\".*?/>",
				"替代替代替代替代替代替代替代替替代替代替代替代替代替代替代替");// NL
													// 30
													// 汉字
		private String regex;
		private String replaceContent;

		private TextRich(String regex, String replaceContent) {
			this.regex = regex;
			this.replaceContent = replaceContent;
		}

		public String getRegex() {
			return regex;
		}

		public String getReplaceContent() {
			return replaceContent;
		}

	}

	/**
	 * 计算长度，一个中文算两个长度， 图片，歌曲，视频长度按照 {@link TextRich} 定义
	 * 
	 * @param dest
	 * @return
	 */
	public static int length(String dest) {
		if (StringUtils.isBlank(dest)) {
			return 0;
		}
		// 小伎俩
		for (TextRich tr : TextRich.values()) {
			dest = dest.replaceAll(tr.getRegex(), tr.getReplaceContent());
		}
		int length = dest.length();
		int total = 0;
		int[] bl = new int[length];
		char[] chars = dest.toCharArray();
		for (int i = 0; i < length; i++) {
			try {
				bl[i] = String.valueOf(chars[i]).getBytes("GBK").length;
				total += bl[i];
			} catch (UnsupportedEncodingException e) {
			}
		}

		return total;
	}

	public static String abbreviate(String dest, int count) {
		if (count <= 0 || StringUtils.isBlank(dest)) {
			return dest;
		}
		int length = dest.length();
		int total = 0;
		int[] bl = new int[length];
		char[] chars = dest.toCharArray();
		for (int i = 0; i < length; i++) {
			try {
				bl[i] = String.valueOf(chars[i]).getBytes("GBK").length;
				total += bl[i];
			} catch (UnsupportedEncodingException e) {
			}
		}
		if (count >= total) {
			return dest;
		}
		StringBuilder sb = new StringBuilder();
		int l = 0;
		for (int i = 0; i < length; i++) {
			l += bl[i];
			if (l > count) {
				sb.append("...");
				break;
			} else {
				sb.append(chars[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 截字 但保留BR BR 长度为一 中英文不区分 <br/>
	 * <br />
	 * <br      />
	 * < br />
	 * 
	 * @param dest
	 * @param count
	 * @return
	 */
	public static String abbreviateSimpleKeepBR(String dest, int count) {
		int length;
		if (dest == null || count <= 0 || (length = dest.length()) <= count) {
			return dest;
		}
		char[] array = dest.toLowerCase().toCharArray();
		StringBuilder sb = new StringBuilder();
		int actualSize = 0;
		int expect = 0;
		StringBuilder tmp = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char c = array[i];

			switch (c) {
			case '<':
				tmp.append('<');
				expect = 1;
				break;
			case 'b':
				if (expect == 1) {
					tmp.append('b');
					expect = 2;
				} else {
					actualSize++;
					sb.append('b');
				}
				break;
			case 'r':
				if (expect == 2) {
					tmp.append('r');
					expect = 3;
				} else {
					actualSize++;
					sb.append('r');
				}
				break;
			case '/':
				if (expect == 3) {
					tmp.append('/');
					expect = 4;
				} else {
					actualSize++;
					sb.append('/');
				}
				break;
			case '>':
				if (expect == 4) {
					expect = 5;
					actualSize++;
					sb.append(tmp.toString());
					sb.append('>');
					tmp = new StringBuilder();
				} else {
					sb.append('>');
				}
				break;
			case ' ':
				if (expect == 1 || expect == 2 || expect == 3 || expect == 4) {
					tmp.append(' ');
				} else {
					sb.append(' ');
				}
				break;
			default:
				if (expect == 1 || expect == 2 || expect == 3 || expect == 4) {
					if (actualSize + tmp.length() > count) {
						sb.append(tmp.substring(0, count - actualSize));
						return sb.toString();
					}
					sb.append(tmp.toString());
					actualSize = actualSize + tmp.length();
					tmp = new StringBuilder();
				}
				actualSize++;
				sb.append(c);
				expect = 0;
				break;
			}
			if (actualSize >= count) {
				break;
			}
		}
		String result = sb.toString();
		return result;
	}

	/**
	 * 去除标点等特殊符号后，是否都是英文，应付大部分情况
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isAllEnglish(String content) {
		boolean allEnglish = true;
		if (StringUtils.isBlank(content)) {
			return false;
		}

		content = content.replaceAll("\\pP|\\pS| ", "");
		content = content.toLowerCase();
		char[] cs = content.toCharArray();
		for (char c : cs) {
			if (c < 'a' || c > 'z') {
				allEnglish = false;
				break;
			}
		}

		return allEnglish;

	}

	public static String formatInteger(long num, int eachDigit, char c) {
		String tmp = String.valueOf(num);
		StringBuilder result = new StringBuilder();
		char[] chars = tmp.toCharArray();
		int index = chars.length - 1;
		for (char ch : chars) {
			result.append(ch);
			if (index % 3 == 0 && index > 0) {
				result.append(c);
			}
			index--;
		}
		return result.toString();
	}

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static final String bytesToHex(byte[] bytes) {
		try {
			int j = bytes.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = bytes[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 拼接接口url
	 * 
	 * @param url
	 *            接口地址
	 * @param map
	 *            参数key和value
	 * @return
	 */
	public static String joinUrlByParameter(String url, Map<String, Object> map) {
		StringBuffer urlBuffer = new StringBuffer();
		urlBuffer.append("?");
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			urlBuffer.append(key).append("=").append(value.toString())
					.append("&");
		}
		url = url.substring(0, url.length() - 1);
		return url;
	}

	/**
	 * 判断是否有空字符串
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isHaveBankParameter(String... params) {

		for (int i = 0; i < params.length; i++) {

			if (StringUtils.isBlank(params[i])) {
				return false;
			}

		}

		return true;
	}

	/**
	 * 判断是否都是数字
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isAllNumeric(String... params) {

		for (int i = 0; i < params.length; i++) {

			if (StringUtils.isBlank(params[i])
					&& (!StringUtils.isNumeric(params[i]))) {
				return false;
			}

		}

		return true;
	}
	
	
	/**
	 * 判断是否有非数字
	 * 
	 * @param params
	 * @return
	 */
	public static boolean isHaveNotNumeric(String... params) {
		
		for (int i = 0; i < params.length; i++) {
			
			if (StringUtils.isBlank(params[i])
					&& (!StringUtils.isNumeric(params[i]))) {
				return true;
			}
			
		}
		
		return false;
	}
}

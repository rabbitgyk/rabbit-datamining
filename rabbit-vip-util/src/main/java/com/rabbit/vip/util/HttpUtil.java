package com.rabbit.vip.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 修改记录：<br>
 * 1.2010-11-24 djg 加入GZIP解析功能.<br>
 * 
 * @author jiangongduan
 * 
 */
public final class HttpUtil {

	private static final Log log = LogFactory.getLog(HttpUtil.class);

	private static final int READ_TIMEOUT = 3000;
	private static final int CONNECT_TIMEOUT = 3000;

	// 失败之后，最大重试次数
	private static final int TRY_COUNT = 3;

	// 请求超过这个时间，就打出来日志
	private static final int MAX_COST_TIME = 5000;

	public static final String readContent(String url) {

		String content = "";
		int count = 0;
		while (true) {
			count++;
			try {
				long begin = System.currentTimeMillis();
				content = readContend(url, 800, 800);
				long cost = System.currentTimeMillis() - begin;
				if (cost > 150) {
					log.info("http time :{url:" + url + "|cost:" + cost + "}");
				}
			} catch (Exception e) {
				log.error("http error:", e);
			}
			if ((StringUtils.isNotBlank(content) && !content.equals("-1"))
					|| count == 3) {
				break;
			}
		}
		return content;
	}

	public static final String readContent(String url, String parameter,
			String enc) {
		String content = "";
		int count = 0;
		while (true) {
			count++;
			try {
				content = readContend(url, parameter, 800, 800, enc);
			} catch (Exception e) {
				log.error("读取错误", e);
			}
			if ((StringUtils.isNotBlank(content) && !content.equals("-1"))
					|| count == TRY_COUNT) {
				break;
			}
		}
		return content;
	}

	public static final String readContend(String url, String parameter,
			int contimeout, int readtimeout, String enc) {
		URL newurl = null;
		BufferedReader reader = null;
		StringBuffer sb = null;
		HttpURLConnection connection = null;
		try {
			newurl = new URL(url);
			connection = (HttpURLConnection) newurl.openConnection();
			connection.setConnectTimeout(contimeout);
			connection.setReadTimeout(readtimeout);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setDoInput(true);
			connection.connect();

			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream(), enc);
			out.write(parameter);
			out.flush();
			out.close();

			InputStream stream = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(stream, enc));
			sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			reader.close();
			connection.disconnect();
			return sb.toString();
		} catch (IOException e) {
			log.error("连接url: " + url + "错误", e);
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					log.error("读关闭错误", e1);
				}
			}
			return "-1";
		} finally {
			url = null;
			if (connection != null)
				connection.disconnect();
		}
	}

	public static final String readContend(String url, int contimeout,
			int readtimeout) {
		URL url1 = null;
		BufferedReader reader = null;
		HttpURLConnection connection = null;
		long begin = System.currentTimeMillis();
		try {
			url1 = new URL(url);
			connection = (HttpURLConnection) url1.openConnection();
			connection.setConnectTimeout(contimeout);
			connection.setReadTimeout(readtimeout);
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			connection.connect();

			String contentEncoding = connection.getContentEncoding();// 编码
			InputStream stream;
			if (null != contentEncoding
					&& -1 != contentEncoding.indexOf("gzip")) {
				stream = new GZIPInputStream(connection.getInputStream());
			} else if (null != contentEncoding
					&& -1 != contentEncoding.indexOf("deflate")) {
				stream = new InflaterInputStream(connection.getInputStream());
			} else {
				stream = connection.getInputStream();
			}
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				sb.append(line).append("\n");
			}
			reader.close();
			connection.disconnect();
			return sb.toString();
		} catch (IOException e) {
			// log.error(e);
			// e.printStackTrace();
			log.error("url: " + url + ",error:" + e.getMessage());
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					log.error("读关闭错误", e1);
				}
			}

			return "-1";
		} finally {
			url1 = null;
			if (connection != null) {
				connection.disconnect();
			}

			long cost = System.currentTimeMillis() - begin;
			if (cost > 100) {
				log.info(String.format("cost %d | url : %s", cost, url));
			}

		}
	}

	/**
	 * 直接返回 {@link net.sf.json.JSONObject},有可能是null
	 * 
	 * @param url
	 * @return
	 */
	public static final JSONObject getResponseObject(String url) {
		String responseStr = getStrResponse(url);
		return getJsonFromResponseString(responseStr);
	}

	/**
	 * POST得到返回结果
	 * 
	 * @param url
	 * @param parameter
	 * @return
	 */
	public static final JSONObject postResponseObject(String url,
			String parameter, String enc) {
		String responseStr = readContent(url, parameter, enc);
		return getJsonFromResponseString(responseStr);
	}

	private static JSONObject getJsonFromResponseString(String responseStr) {
		if (StringUtils.isEmpty(responseStr)) {
			return null;
		}
		JSONObject result = null;
		try {
			result = (JSONObject) JSONObject.fromObject(responseStr);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("", e);
		}
		return result;
	}

	public static final String getStrResponse(String url) {
		long begin = System.currentTimeMillis();
		String content = "";
		int count = 0;
		while (true) {
			count++;
			try {
				content = readContend(url);
			} catch (Exception e) {
				log.error(e);
			}
			if ((StringUtils.isNotBlank(content) && !content.equals("-1"))
					|| count == TRY_COUNT) {
				break;
			}
		}
		long cost = System.currentTimeMillis() - begin;
		if ((cost > MAX_COST_TIME)) {
			log.debug(String
					.format("httpClinet  url : %s , cost:%d", url, cost));
		}
		return content;
	}

	private static final String readContend(String url) {
		BufferedReader reader = null;
		HttpURLConnection connection = getConnection(url);

		if (connection == null) {
			return "";
		}
		try {
			connection.connect();
			String contentEncoding = connection.getContentEncoding();// 编码
			InputStream stream;
			if (null != contentEncoding
					&& -1 != contentEncoding.indexOf("gzip")) {
				stream = new GZIPInputStream(connection.getInputStream());
			} else if (null != contentEncoding
					&& -1 != contentEncoding.indexOf("deflate")) {
				stream = new InflaterInputStream(connection.getInputStream());
			} else {
				stream = connection.getInputStream();
			}
			reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			reader.close();
			connection.disconnect();
			return sb.toString();
		} catch (IOException e) {
			log.error("url: " + url + ",error:" + e.getMessage());
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					log.error("读关闭错误", e1);
				}
			}

			return "-1";
		} finally {
			if (connection != null)
				connection.disconnect();
		}
	}

	private static HttpURLConnection getConnection(String url) {
		HttpURLConnection connection = null;
		URL url1 = null;
		try {
			url1 = new URL(url);
			connection = (HttpURLConnection) url1.openConnection();
			connection.setConnectTimeout(CONNECT_TIMEOUT);
			connection.setReadTimeout(READ_TIMEOUT);
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
		} catch (Exception e) {
			log.error(e);
		}

		return connection;
	}
}

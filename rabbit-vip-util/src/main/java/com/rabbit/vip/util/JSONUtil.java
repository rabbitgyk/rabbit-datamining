package com.rabbit.vip.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: json相关处理
 * 
 * @author tengfei@letv.com
 * 
 * @date 2013年9月2日 下午6:19:11
 */
public class JSONUtil {
	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
	private static ObjectMapper objectMapper = null;
	private static MappingJsonFactory jsonFactory = new MappingJsonFactory();

	static {
		objectMapper = new ObjectMapper();
		objectMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2map(String json) {
		Map<String, Object> mapper = null;
		try {
			mapper = objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error("error when transfer json to map,json data is:\t" + json, e);
			return null;
		}
		return mapper;
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2MapEngoreLogger(String json) {
		Map<String, Object> mapper = null;
		try {
			mapper = objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			return null;
		}
		return mapper;
	}

	@SuppressWarnings("unchecked")
	public static <E, T> Map<E, T> jsonET2Map(String json) {
		Map<E, T> mapper = null;
		try {
			mapper = objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error("error when transfer json to map,json data is:\t" + json, e);
			return null;
		}
		return mapper;
	}

	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> jsonT2Map(String json) {
		Map<String, T> mapper = null;
		try {
			mapper = objectMapper.readValue(json, Map.class);
		} catch (Exception e) {
			logger.error("error when transfer json to map,json data is:\t" + json, e);
			return null;
		}
		return mapper;
	}

	public static Map<String, Object> object2map(Object object) {
		Map<String, Object> mapper = null;
		try {
			mapper = json2map(object2json(object));
		} catch (Exception e) {
			logger.error("error when transfer json to map", e);
			return null;
		}
		return mapper;
	}

	public static <E, T> String mapET2Json(Map<E, T> map) {
		String result = null;
		if (map != null) {
			try {
				StringWriter writer = new StringWriter();
				objectMapper.writeValue(writer, map);
				result = writer.toString();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return result;
	}

	public static <T> String mapT2Json(Map<String, T> map) {
		String result = null;
		if (map != null) {
			try {
				StringWriter writer = new StringWriter();
				objectMapper.writeValue(writer, map);
				result = writer.toString();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return result;
	}

	public static String map2Json(Map<String, ? extends Object> map) {
		String result = null;
		if (map != null) {
			try {
				StringWriter writer = new StringWriter();
				objectMapper.writeValue(writer, map);
				result = writer.toString();
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return result;
	}

	public static String appendInfo(String info, Map<String, String> extra) {
		String result = info;
		try {
			Map<String, Object> infoMap = json2map(info);
			if (infoMap != null && extra != null) {
				for (Entry<String, String> entry : extra.entrySet()) {
					infoMap.put(entry.getKey(), entry.getValue());
				}
				result = map2Json(infoMap);
			}
		} catch (Exception e) {
			logger.error("", e);
			result = info;
		}
		return result;
	}

	public static <T> List<T> json2listFailWithException(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			return Collections.emptyList();
		}
		try {
			List<T> result = new ArrayList<T>();
			Field[] fields = clazz.getDeclaredFields();
			Field[] parentFields = null;
			Class<? super T> parentClazz = clazz.getSuperclass();
			if (parentClazz != null) {
				parentFields = parentClazz.getDeclaredFields();
			}
			@SuppressWarnings("unchecked")
			List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
			for (LinkedHashMap<String, Object> objectMap : list) {
				T pojo = (T) clazz.newInstance();
				for (Entry<String, Object> entry : objectMap.entrySet()) {
					if (parentFields != null) {
						for (Field field : parentFields) {
							if (field.getName().equals(entry.getKey())) {
								field.setAccessible(true);
								if (!field.isEnumConstant()) {
									if (field.getGenericType().equals(Date.class)) {
										field.set(pojo, new Date(Long.parseLong(JSONUtil.object2json(entry.getValue()))));
									} else {

										field.set(pojo, entry.getValue());
									}
								}
							}
						}
					}

					for (Field field : fields) {
						if (field.getName().equals(entry.getKey())) {
							field.setAccessible(true);
							if (!field.isEnumConstant()) {
								if (field.getGenericType().equals(Date.class)) {
									field.set(pojo, new Date(Long.parseLong(JSONUtil.object2json(entry.getValue()))));
								} else {

									field.set(pojo, entry.getValue());
								}
							}
						}
					}

				}
				result.add(pojo);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException("parse to list fail", e);
		}

	}

	public static <T> List<T> json2list(String json, Class<T> clazz) {
		try {
			return json2listFailWithException(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
		return Collections.emptyList();
	}

	public static <T> T json2Object(String json, Class<T> clazz) {
		T t = null;
		try {
			t = (T) objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error when transfer json to map", e);
			return null;
		}
		return t;
	}

	/**
	 * 转换Object到Json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String object2json(Object object) {
		StringWriter writer = new StringWriter();
		JsonGenerator jsonGenerator = null;
		try {
			jsonGenerator = jsonFactory.createJsonGenerator(writer);
		} catch (IOException e) {
			throw new RuntimeException("json factory init error", e);
		}
		try {
			objectMapper.writeValue(jsonGenerator, object);
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException("object wapped by json error", e);
		}
		return writer.getBuffer().toString();
	}

}

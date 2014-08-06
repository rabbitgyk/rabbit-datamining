package com.rabbit.vip.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author tf
 *
 */
public class CollectionsUtil {
	 
	/**
	 * 
	 * @param c1
	 * @param s1
	 * @return c1 - s1
	 * 
	 */
	public static <T> List<T> minius(Collection<T> c1 , Collection<T> s1){ 
		
		if(c1 == null){
			return Collections.emptyList();
		}
		
		if(s1 == null || s1.size() == 0){
			return new ArrayList<T>(c1);
		}
		List<T> list = new ArrayList<T>();
		for(T t : c1){
			if(! s1.contains(t)){
				list.add(t);
			}
		}
		return list;
	}
	
	
	public static <T> List<T> minius(Collection<T> c1 , Set<T> s1){ 
		
		if(c1 == null){
			return Collections.emptyList();
		}
		
		if(s1 == null || s1.size() == 0){
			return new ArrayList<T>(c1);
		}
		List<T> list = new ArrayList<T>();
		for(T t : c1){
			if(! s1.contains(t)){
				list.add(t);
			}
		}
		return list;
	}
	
	
	
	
	/**
	 * 从 srcList 中取 从 positon 开始的 count 个值 如果到末尾则继续从头开始 直到取完为止 
	 * @param <T>
	 * @param srcList
	 * @param position
	 * @return
	 */
	public static<T> List<T> circleSubList(List<T> srcList , int position , int count ){
		if( srcList == null || srcList.size() == 0 ){
			return Collections.emptyList();
		}
		position = position < 0 ? 0 : position;
		count = count > srcList.size() ? srcList.size() : count;
		if( position > srcList.size()-1 ){
			return srcList.subList(0 , count);
		}
		if( position + count <= srcList.size() ){
			return srcList.subList( position , ( position + count ) );
		}
		List<T> fList = srcList.subList(position , srcList.size() );
		List<T> eList = srcList.subList(0 , count - srcList.size() + position );
		fList.addAll(eList);
		return fList;
	}
	
	
	
	
	public static void main(String[] args){
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0 ; i < 10 ; i++){
			list.add(i);
			list.add(i);
		}
		System.out.println(list);
//		list.remove(i);
		list = uiqList(list);
		System.out.println(list);
		
		
		
	}
	
	
	
	
	/**
	 * 如果是null，那么给返回一个空的
	 * @param list
	 * @return
	 */
	public static <T>  List<T> checkEmptyList(List<T> list){
		if(list == null){
			return Collections.emptyList();
		}else{
			return list;
		}
	}
	
	/**
	 * 是否为空
	 * @param list
	 * @return
	 */
	public static <T> boolean isEmpty(List<T> list){
		return list == null || list.isEmpty();
	}
	
	public static <K,V> boolean isEmpty(Map<K,V> map){
		return map == null || map.isEmpty();
	}
	
	public static <K,V> boolean isNotEmpty(Map<K,V> map){
		return !isEmpty(map);
	}
	
	public static <T> boolean isNotEmpty(List<T> list){
		return !isEmpty(list);
	}
	
	
	/**
	 * 大小，如果null,返回0
	 * @param list
	 * @return
	 */
	public static <T> int getSize(List<T> list){
		int size = 0;
		
		if(isEmpty(list)){
			size =  0;
		}else{
			size = list.size();
		}
		
		return size;
	}
	
	/**
	 * 去重复,保证顺序
	 * @param list
	 * @return
	 */
	public static <T> List<T> uiqList(List<T> list){
		if(list == null){
			return list;
		}

		List<T> tList = new ArrayList<T>(list.size());
		for(T t : list){
			if(!tList.contains(t)){
				tList.add(t);
			}
		}
		return tList;
	}
	
	/**
	 * 按照list中对象的某个字段，去重list
	 * @param list
	 * @param field
	 * @return
	 
	public static <T> List<T> uiqListByField(List<T> list, String fieldName){
		if(list == null){
			return list;
		}

		String[] types1={"int","java.lang.String","boolean","char","float","double","long","short","byte"};
		String[] types2={"Integer","java.lang.String","java.lang.Boolean","java.lang.Character","java.lang.Float","java.lang.Double","java.lang.Long","java.lang.Short","java.lang.Byte"};  
	  
		Map<Object, T> map = new HashMap<Object, T>();
		for(T t : list){
			Object value = null;
			try {
				Field field = t.getClass().getDeclaredField(fieldName);
				for(int i = 0; i<types1.length; i++){
					if(field.getType().getName().equalsIgnoreCase(types1[i]) || 
							field.getType().getName().equalsIgnoreCase(types2[i])){ 
						value = field.get(t);
					}
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(map.get(value) != null){
				list.remove(t);
			}else{
				map.put(value, t);
			}
		}
		return list;
	}*/
	
	/**
	 * 页号不能小于1，页大小不能小于1，如果页号超过最大值直接返回第一页
	 * @param list 列表
	 * @param page 页号：从1开始
	 * @param size 页大小
	 * @return
	 * public static void main(String[] args) {
		List l = Arrays.asList(1,2,3,4,5,6);
		System.out.println(pageList(l, 1, 0).toString());
		System.out.println(pageList(l, 1, 4).toString());
		System.out.println(pageList(l, 2, 4).toString());
		System.out.println(pageList(l, 3, 4).toString());
		System.out.println(pageList(l, 4, 4).toString());
	}
	 * 
	 * 
	 */
	public static <T> List<T> pageList(List<T> list, int page, int size) {
		if (page < 1) {
			throw new IllegalArgumentException("page should not little than 1,page:" + page);
		}
		if (size < 1) {
			throw new IllegalArgumentException("size should not little than 1,size:" + size);
		}
		if (isEmpty(list)) {
			return Collections.emptyList();
		}
		int totalSize = list.size();

		int fromIndex = (page - 1) * size > (totalSize - 1) ? 0 : (page - 1) * size;

		int endIndex = (fromIndex + size) > (totalSize) ? (totalSize) : (fromIndex + size);

		return list.subList(fromIndex, endIndex);

	}
}

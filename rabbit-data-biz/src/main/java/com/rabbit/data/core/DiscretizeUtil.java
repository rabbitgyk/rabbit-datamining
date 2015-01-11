package com.rabbit.data.core;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.supervised.attribute.Discretize;

/**
 * 数据预处理之离散化工具类
 * 
 * @author rabbit
 * @date   Dec 30, 2014
 */
public class DiscretizeUtil {

	/**
	 * 原数据集不变得到新的离散化之后的数据集
	 * @param instances
	 * @return
	 * @throws Exception
	 */
	public static Instances discretize(Instances instances) throws Exception {
		Instances discInstances = new Instances(instances);
		Discretize disTransform = new Discretize();
	    disTransform.setUseBetterEncoding(true);
	    disTransform.setInputFormat(discInstances);
	    discInstances = Filter.useFilter(discInstances, disTransform);
	    return discInstances;
	}
}

/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.ideal.audit.common.dao.mapper;

import com.google.common.collect.Lists;
import com.ideal.audit.common.util.StringUtils;
import org.dozer.DozerBeanMapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 
 * 1. 持有Mapper的单例.
 * 2. 返回值类型转换.
 * 3. 批量转换Collection中的所有对象.
 * 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 * 
 * @author calvin
 */
public class BeanMapper {

	/**
	 * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
	 */
	private static DozerBeanMapper dozer = new DozerBeanMapper();

	/**
	 * 基于Dozer转换对象的类型.
	 */
	public static <T> T map(Object source, Class<T> destinationClass) {
		return dozer.map(source, destinationClass);
	}

	/**
	 * 基于Dozer转换Collection中对象的类型.
	 */
	public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
		List<T> destinationList = Lists.newArrayList();
		for (Object sourceObject : sourceList) {
            //处理字段对应属性
            sourceObject = convertKey(sourceObject);
			T destinationObject = dozer.map(sourceObject, destinationClass);
			destinationList.add(destinationObject);
		}
		return destinationList;
	}

	/**
	 * 基于Dozer将对象A的值拷贝到对象B中.
	 */
	public static void copy(Object source, Object destinationObject) {
		dozer.map(source, destinationObject);
	}

    public static Object convertKey(Object sourceObject){
        if (sourceObject instanceof Map) {
            Map<String, Object> sourceMap = (Map<String, Object>) sourceObject;
            Map<String, Object> map = new HashMap<String, Object>();
            for (Map.Entry<String, Object> entry : sourceMap.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                if (!entry.getKey().contains("_")) {
                    map.put(entry.getKey().toLowerCase(), entry.getValue());
                } else {
                    map.put(StringUtils.getPropertyByColumn(entry.getKey()), entry.getValue());
                }
            }
            return map;
        }
        return sourceObject;
    }

    /**Test方法
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("NAME", "标签");
        map.put("USER_NAME", "标签222222");
        List list = new ArrayList();
        list.add(map);
        List<DataBean> result = mapList(list, DataBean.class);
        for (DataBean dataBean : result) {
            System.out.println(dataBean.getName());
            System.out.println(dataBean.getUserName());
        }
    }
    */
}
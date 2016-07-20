package com.oriental.finance.cat.monitor.common.util;

import com.google.common.collect.Lists;
import org.dozer.CustomConverter;
import org.dozer.CustomFieldMapper;
import org.dozer.DozerBeanMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;

import java.util.List;

/**
 * 描述：Bean拷贝Utils
 * <p>
 * #
 * </p>
 * User: lyd Date: 2016/7/8 ProjectName:cat-monitor Version: 1.0
 */
public class BeanCopyUtils {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T copy(Object source, Class<T> destinationClass) {
        if(source == null){
            return null;
        }
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        if(source != null)
        {
            dozer.map(source, destinationObject);
        }
    }

    /**
     * 将源集合转换为目标集合,注意:目标集合是新建的
     * @param srcList 源集合
     * @param descType 目标集合中元素的类型
     */
    public static <T> List<T> copyList(List srcList, Class<T> descType){
        if(srcList == null) return null;
        List<T> descList = Lists.newArrayList();
        for(Object obj : srcList){
            T t = copy(obj, descType);
            descList.add(t);
        }
        return descList;
    }

}

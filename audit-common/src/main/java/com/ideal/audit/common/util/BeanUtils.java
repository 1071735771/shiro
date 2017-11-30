package com.ideal.audit.common.util;


import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingsen on 15/2/27.
 */
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * 对象合并，当目标属性值为空且源属性值不为空时，将源属性值赋给目标属性值
     * @param src
     * @param destination
     * @param <M>
     * @return
     */
    public static <M> M merge(M src, M destination) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(destination.getClass());

            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                if (descriptor.getWriteMethod() != null && null==descriptor.getReadMethod().invoke(destination)) {
                    //源属性值
                    Object originalValue = descriptor.getReadMethod().invoke(src);
                    if (originalValue != null) {
                        //写入非null
                        descriptor.getWriteMethod().invoke(destination, originalValue);
                    }
                }
            }
            return destination;
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressWarnings("unchecked")
    public static <T>  List<T> copyListProperties(List sources,Class targetClazz) {
        List targerts =  new ArrayList(sources.size());
        try{
            for (int i=0;i<sources.size();i++){
                Object obj = targetClazz.newInstance();
                copyProperties(sources.get(i), obj);
                targerts.add((T)obj);
            }
            return targerts;
        }catch (IllegalAccessException e){
            throw new RuntimeException(e);
        }catch (InstantiationException e1){
            throw new RuntimeException(e1);
        }
    }
}

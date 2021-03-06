package com.ideal.audit.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 常用的类处理 。
 */
public class ClassUtils {
	/**
	 * 取得某个类中指定字段的值,调用类的getter方法 。
	 * @param classObject
	 *            指定的类
	 * @param fieldName
	 *            指定的字段名。
	 * @return 如果取值失败，则抛出异常。
	 */
	public static Object invokeGetter(Object classObject, String fieldName) throws Exception {
		String first = fieldName.substring(0, 1);
		String getter = "get" + fieldName.replaceFirst(first, first.toUpperCase());
		Object value = null;
		Method method = classObject.getClass().getDeclaredMethod(getter, null);
		value = method.invoke(classObject, null);
		return value;
	}
	/**
	 * 调用一个类的setter方法。
	 * @param classObject 目标类
	 * @param fieldName 字段名
	 * @param value 需要set的值
	 * @throws Exception
	 */
	public static void invokeSetter(Object classObject, String fieldName, Object value) throws Exception {
		String first = fieldName.substring(0, 1);
		String setter = "set" + fieldName.replaceFirst(first, first.toUpperCase());
		Method method = classObject.getClass().getDeclaredMethod(setter,value.getClass());
		value = method.invoke(classObject, value);
	}
	/**
	 * 读取某类的某个字段值，侵入式，不调用 getter
	 * @param classObject 待取值的对象。
	 * @param fieldName  字段名
	 * @return  字段值
	 * @throws Exception
	 */
	public static Object getFieldValue(Object classObject, String fieldName) throws Exception {
		Object ret = null;
		if(classObject!=null){
			Class cls =classObject.getClass();
			Field field= cls.getDeclaredField(fieldName);
			field.setAccessible(true);
			ret = field.get(classObject);
			field.setAccessible(false);
		}
		return ret;
	}
	/**
	 * 设置某类的某个字段值，侵入式，不调用 setter
	 * @param classObject 待取值的对象。
	 * @param fieldName  字段名
	 * @param value  字段值
	 * @throws Exception
	 */
	public static void setFieldValue(Object classObject, String fieldName, Object value) throws Exception {
		if(classObject!=null){
			Class cls =classObject.getClass();
			Field field= cls.getDeclaredField(fieldName);
			field.setAccessible(true);
			if(value != null && ("BigDecimal").equals(value.getClass().getSimpleName())){
				if(field.getType().getSimpleName().equals("Long")) {
					value = ((BigDecimal) value).longValue();
				}else if(field.getType().getSimpleName().equals("Double")){
					value = ((BigDecimal)value).doubleValue();
				}
			}
			field.set(classObject, field.getType().cast(value));
			field.setAccessible(false);
		}
	}
	/**
	 * 取某class的所有属性，不包括父对象属性。
	 * @param cls
	 * @return 
	 * @throws Exception
	 */
	public static List<String> getClassFields(Class cls) {
		ArrayList<String> list = new ArrayList<String>();
		Field[] fields = cls.getDeclaredFields();
		for(Field x:fields){
			list.add(x.getName());
		}
		return list;
	}

	/**
	 * 获取class所有属性和值的map集合，不包括父对象属性。
	 * @param object
	 * @return
     */
	public static Map<String,Object> getFieldValMap(Object object) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		Field[] fields = object.getClass().getDeclaredFields();
		for(Field x:fields){
			map.put(x.getName(),invokeGetter(object,x.getName()));
		}
		return map;
	}
	/**
	 * 在文件系统中获取指定包下的所有Class
	 * 
	 * @param packageName
	 *            包名，如cn.com.jsoft.server
	 * @param packageDir
	 *            包路径，如cn/com/jsoft/server
	 * @param recursive
	 *            是否递归子目录
	 * @param classes
	 *            用来收集包下面的所有类和接口。
	 */
	public static void findClassesInFilePackage(String packageName, File packageDir, final boolean recursive, List<Class> classes) {

		if (!packageDir.exists() || !packageDir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = packageDir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findClassesInFilePackage(packageName + "." + file.getName(), file, recursive, classes);
			} else {
				try {
					String className = file.getName().substring(0, file.getName().length() - 6);
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 在jar包里面获取指定包下的所有Class
	 * 
	 * @param packageName
	 *            包名，如cn.com.jsoft.server
	 * @param jarFile
	 *            jar包路径
	 * @param recursive
	 *            是否递归子目录
	 * @param classes
	 *            用来收集包下面的所有类和接口。
	 */
	public static void findClassesInJarPackage(String packageName, JarFile jarFile, boolean recursive, List<Class> classes) {
		String packageDirName = packageName.replace('.', '/');
		Enumeration<JarEntry> entries = jarFile.entries(); // 遍历zip中所有的条目
		while (entries.hasMoreElements()) {
			JarEntry entry = entries.nextElement();
			String name = entry.getName();
			if (name.charAt(0) == '/') {
				name = name.substring(1);
			}

			if (name.startsWith(packageDirName)) { // 如果是扫描的包 /j/k/j/hhh.class
				int idx = name.lastIndexOf('/'); // 如果以"/"结尾 是一个包
				if (idx != -1) {
					packageName = name.substring(0, idx).replace('/', '.');
				}
				if ((idx != -1) || recursive) {
					if (name.endsWith(".class") && !entry.isDirectory()) {
						String className = name.substring(packageName.length() + 1, name.length() - 6);
						try {
							// 添加到classes
							classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 从系统classpath下面(包含classes目录和jar包)获取指定包下面的所有类和接口。
	 * 
	 * @param pack
	 *            包名，如 cn.com.jsoft.server
	 * @return 本包和子包下面的所有类和接口。如果没有则返回空set
	 */
	public static List<Class> getClassesInPackage(String pack) {
		List<Class> classes = new LinkedList<Class>();

		boolean recursive = true; // 是否循环迭代
		String packageName = pack; // 获取包的名字 并进行替换
		String packageDirName = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {

					File filePath = new File(URLDecoder.decode(url.getFile(), "UTF-8")); // 获取包的物理路径
					findClassesInFilePackage(packageName, filePath, recursive, classes);
				} else if ("jar".equals(protocol)) {

					JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile(); // 获取jar
					findClassesInJarPackage(pack, jar, recursive, classes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 将bean转换成map.
	 * @param classObject
	 * @return
	 */
	public static Map<String, Object> bean2Map(Object classObject) {
		Map<String, Object> ret = new HashMap<String, Object>();
		if (classObject != null) {
			Class cls = classObject.getClass();
			Field[] field = cls.getDeclaredFields();
			for (Field x : field) {
				String key = x.getName();
				Object value;
				try {
					value = invokeGetter(classObject, key);
					ret.put(key, value);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 循环向上转型, 获取对象的 DeclaredMethod
	 * @param object : 子类对象
	 * @param methodName : 父类中的方法名
	 * @param parameterTypes : 父类中的方法参数类型
	 * @return 父类中的方法对象
	 */
	public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes){
		Method method = null ;

		for(Class<?> clazz = object.getClass(); clazz != Object.class ; clazz = clazz.getSuperclass()) {
			try {
				method = clazz.getDeclaredMethod(methodName, parameterTypes) ;
				return method ;
			} catch (Exception e) {
				//这里什么都不要做！并且这里的异常必须这样写，不能抛出去。
				//如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
			}
		}
		return null;
	}

	/**
	 * 直接调用对象方法, 而忽略修饰符(private, protected, default)
	 * @param object : 子类对象
	 * @param fieldName : 父类中的属性名
	 * @return 父类中方法的执行结果
	 */
	public static Object invokeParentGetter(Object object, String fieldName) {
		//根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
		String first = fieldName.substring(0, 1);
		String getter = "get" + fieldName.replaceFirst(first, first.toUpperCase());
		Method method = getDeclaredMethod(object, getter, null) ;
		//抑制Java对方法进行检查,主要是针对私有方法而言
		method.setAccessible(true) ;
		try {
			if(null != method) {
				//调用object 的 method 所代表的方法，其方法的参数是 parameters
				return method.invoke(object) ;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

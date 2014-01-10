package com.freestrings.shreder;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 
 * @author freestrings
 * 
 */
public class Types {

	public final static String ARRAY = "java.Array";

	private final static Set<Class<?>> primitives = new HashSet<Class<?>>();

	static {
		primitives.add(String.class);
		primitives.add(Boolean.class);
		primitives.add(Integer.class);
		primitives.add(Float.class);
		primitives.add(Double.class);
		primitives.add(Long.class);
		primitives.add(Number.class);
		primitives.add(Byte.class);
		primitives.add(Character.class);
		primitives.add(Short.class);
		primitives.add(java.sql.Date.class);
		primitives.add(java.util.Date.class);
		primitives.add(Timestamp.class);
		primitives.add(BigDecimal.class);
		primitives.add(BigInteger.class);
		primitives.add(AtomicInteger.class);
		primitives.add(AtomicLong.class);
	}

	private final static Set<String> primitives2 = new HashSet<String>();

	static {
		for (Class<?> c : primitives) {
			primitives2.add(c.getName());
		}
	}

	private final static Map<Class<?>, String> java2Json = new HashMap<Class<?>, String>();

	static {
		java2Json.put(String.class, "string");
		java2Json.put(Boolean.class, "boolean");
		java2Json.put(Integer.class, "integer");
		java2Json.put(Float.class, "number");
		java2Json.put(Double.class, "number");
		java2Json.put(Long.class, "number");
		java2Json.put(Number.class, "number");
		java2Json.put(Byte.class, "integer");
		java2Json.put(Character.class, "string");
		java2Json.put(Short.class, "integer");
		java2Json.put(java.sql.Date.class, "datetime");
		java2Json.put(java.util.Date.class, "datetime");
		java2Json.put(Timestamp.class, "datetime");
		java2Json.put(List.class, "array");
		java2Json.put(Collection.class, "array");
		java2Json.put(Enum.class, "enum");
		java2Json.put(Map.class, "object");
		java2Json.put(BigDecimal.class, "number");
		java2Json.put(BigInteger.class, "number");
		java2Json.put(AtomicInteger.class, "number");
		java2Json.put(AtomicLong.class, "number");
	}

	private final static Map<String, String> java2Json2 = new HashMap<String, String>();

	static {
		Iterator<Class<?>> iterator = java2Json.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> c = (Class<?>) iterator.next();
			String value = java2Json.get(c);
			java2Json2.put(c.getName(), value);
		}
	}

	private static final Set<Class<?>> validationConstraints = new HashSet<Class<?>>();

	static {
		validationConstraints.add(Digits.class);
		validationConstraints.add(Digits.List.class);
		validationConstraints.add(Future.class);
		validationConstraints.add(Future.List.class);
		validationConstraints.add(Min.class);
		validationConstraints.add(Min.List.class);
		validationConstraints.add(Max.class);
		validationConstraints.add(Max.List.class);
		validationConstraints.add(Past.class);
		validationConstraints.add(Past.List.class);
		validationConstraints.add(NotNull.class);
		validationConstraints.add(NotNull.List.class);
		validationConstraints.add(DecimalMax.class);
		validationConstraints.add(DecimalMax.List.class);
		validationConstraints.add(DecimalMin.class);
		validationConstraints.add(DecimalMin.List.class);
		validationConstraints.add(Pattern.class);
		validationConstraints.add(Pattern.List.class);
		validationConstraints.add(AssertTrue.class);
		validationConstraints.add(AssertTrue.List.class);
		validationConstraints.add(AssertFalse.class);
		validationConstraints.add(AssertFalse.List.class);
		validationConstraints.add(Null.class);
		validationConstraints.add(Null.List.class);
		validationConstraints.add(Size.class);
		validationConstraints.add(Size.List.class);
	}
	
	private static final Set<String> validationConstraints2 = new HashSet<String>();
	
	static {
		Iterator<Class<?>> iterator = validationConstraints.iterator();
		while (iterator.hasNext()) {
			Class<?> c = (Class<?>) iterator.next();
			String name = c.getName();
			if(name.indexOf("$") > -1) {
				name = name.replace('$', '.');
			}
			validationConstraints2.add(name);
		}
	}

	private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<Class<?>, Class<?>>();
	static {
		primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
		primitiveWrapperMap.put(Byte.TYPE, Byte.class);
		primitiveWrapperMap.put(Character.TYPE, Character.class);
		primitiveWrapperMap.put(Short.TYPE, Short.class);
		primitiveWrapperMap.put(Integer.TYPE, Integer.class);
		primitiveWrapperMap.put(Long.TYPE, Long.class);
		primitiveWrapperMap.put(Double.TYPE, Double.class);
		primitiveWrapperMap.put(Float.TYPE, Float.class);
		primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
	}

	private static final Map<String, String> primitiveWrapperMap2 = new HashMap<String, String>();
	static {
		Iterator<Class<?>> iterator = primitiveWrapperMap.keySet().iterator();
		while (iterator.hasNext()) {
			Class<?> key = iterator.next();
			Class<?> value = primitiveWrapperMap.get(key);
			primitiveWrapperMap2.put(key.toString(), value.getName());
		}
	}

	@SuppressWarnings("rawtypes")
	public static Class primitiveToWrapper(Class cls) {
		Class convertedClass = cls;
		if (cls != null && cls.isPrimitive()) {
			convertedClass = (Class) primitiveWrapperMap.get(cls);
		}
		return convertedClass;
	}

	public static String primitiveToWrapper(String cls) {
		String convertedName = primitiveWrapperMap2.get(cls);
		if (convertedName != null) {
			return convertedName;
		}
		return cls;
	}

	public static boolean isPrimitive(Class<?> clazz) {
		return primitives.contains(primitiveToWrapper(clazz));
	}

	public static boolean isPrimitive(String name) {
		return primitives2.contains(name);
	}

	public static String toJsonType(Class<?> clazz) {
		return java2Json.get(primitiveToWrapper(clazz));
	}

	public static String toJsonType(String clazz) {
		String ret = java2Json2.get(primitiveToWrapper(clazz));
		if (ret == null) {
			return "object";
		}
		return ret;
	}

	public static boolean isBuiltInConstraints(Class<? extends Annotation> clazz) {
		return validationConstraints.contains(clazz);
	}
	
	public static boolean isBuiltInConstraints(String clazz) {
		return validationConstraints2.contains(clazz);
	}

}

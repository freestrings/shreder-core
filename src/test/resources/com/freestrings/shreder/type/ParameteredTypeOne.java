package com.freestrings.shreder.type;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.freestrings.shreder.type.AnnotationTest.MyEnum;

/**
 * 
 * class:ParameteredTypeOne<T>
 * 
 * @author freestrings
 * 
 */
public class ParameteredTypeOne<T> {

	@Max(value = 0)
	private T t;

	@NotNull
	@AnnotationTest(value = MyEnum.E2)
	private String t2;

	/**
	 * method:getT
	 * 
	 * @return
	 */
	public T getT() {
		return t;
	}

	public String getT2() {
		return null;
	}
}

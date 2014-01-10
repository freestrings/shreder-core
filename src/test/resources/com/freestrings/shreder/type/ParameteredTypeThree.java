package com.freestrings.shreder.type;

import javax.validation.constraints.NotNull;

import com.freestrings.shreder.type.AnnotationTest.MyEnum;

public class ParameteredTypeThree {

	@NotNull
	@AnnotationTest(value = MyEnum.E2)
	private String value;

	@AnnotationTest(value = MyEnum.E1)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

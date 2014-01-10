package com.freestrings.shreder.type;

import javax.validation.constraints.NotNull;

public class Parent {

	@NotNull
	private int parentValue;

	public int getParentValue() {
		return parentValue;
	}

}

package com.freestrings.shreder.type;

import javax.validation.constraints.AssertTrue;

public class Child2 extends Parent {

	@AssertTrue
	int parentValue;

	@Override
	public int getParentValue() {
		return super.getParentValue();
	}

}

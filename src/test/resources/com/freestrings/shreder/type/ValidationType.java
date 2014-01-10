package com.freestrings.shreder.type;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class ValidationType {

	@Max(value = 100)
	@NotNull
	private int intValue;

	@Digits(fraction = 1, integer = 10)
	private boolean booleanValue;

	@Digits.List(value = { @Digits(fraction = 3, integer = 4), @Digits(fraction = 5, integer = 6) })
	private List<Integer> list;

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

}

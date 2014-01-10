package com.freestrings.shreder.type;

import java.util.Map;

public interface WildcardTypeOne {

	public ParameteredTypeOne<? extends Map<String, String>> get1();
}

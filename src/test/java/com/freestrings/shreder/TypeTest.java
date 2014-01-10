package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class TypeTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void type1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/TypeTest1"));
		Assert.assertEquals("$.response.rawType", "java.math.BigDecimal");
		Assert.assertEquals("$.response.type", "number");
	}
}

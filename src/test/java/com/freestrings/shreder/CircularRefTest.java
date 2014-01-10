package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class CircularRefTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void circularRef1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CircularRefTest1"));
		Assert.assertEquals("$.response.members[0].type", "ref");
	}
	
	@Test
	public void circularRef2() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CircularRefTest2"));
		Assert.assertEquals("$.response.rawType", "com.freestrings.shreder.type.CircularRefTypeTwo");
		Assert.assertEquals("$.response.members[0].rawType", "com.freestrings.shreder.type.CircularRefTypeTwo");
		Assert.assertEquals("$.response.members[0].members[0].rawType", "com.freestrings.shreder.type.ParameteredTypeOne");
		Assert.assertEquals("$.response.members[0].members[0].members[0].rawType", "java.lang.String");
	}
	
	@Test
	public void circularRef3() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CircularRefTest3"));
		Assert.assertEquals("$.response.members[0].members[0].type", "ref");
	}
	
	@Test
	public void circularRef4() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CircularRefTest4"));
		Assert.assertEquals("$.response.members[0].members[0].type", "ref");
	}
	
}

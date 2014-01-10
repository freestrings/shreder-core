package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class AsyncTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void async1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AsyncTest1"));
		Assert.assertEquals("$.response.rawType", "java.lang.String");
	}

	@Test
	public void async2() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AsyncTest2"));
		Assert.assertEquals("$.response.rawType", "java.lang.String");
	}

	@Test
	public void async3() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AsyncTest3"));
		Assert.assertEquals("$.response.rawType", "java.lang.String");
	}

	@Test
	public void async4() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AsyncTest4"));
		Assert.assertEquals("$.response.rawType", "java.lang.String");
	}

}

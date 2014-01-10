package com.freestrings.shreder;

import java.util.List;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;
import com.jayway.jsonpath.JsonPath;

public class InheritTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void inherit1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/InheritTest1"));
		Assert.assertEquals("$.response.members[0].name", "childValue");
		Assert.assertEquals("$.response.members[1].name", "parentValue");
	}

	@Test
	public void inherit2() throws Exception {
		String jsonStr = run("com/freestrings/shreder/InheritTest2");
		JsonAsserter Assert = JsonAssert.with(jsonStr);
		Object read = JsonPath.read(jsonStr, "$.response.members");
		junit.framework.Assert.assertEquals(((List<?>)read).size() , 1);
		Assert.assertEquals("$.response.members[0].name", "parentValue");
		Assert.assertNotNull("$.response.members[0].annotations.AssertTrue");
	}
}

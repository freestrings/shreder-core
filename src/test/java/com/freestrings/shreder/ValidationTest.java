package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class ValidationTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void validation1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/ValidationTest1"));

		Assert.assertEquals("$.response.members[0].name", "intValue");
		Assert.assertNotNull("$.response.members[0].annotations.NotNull");
		Assert.assertEquals("$.response.members[0].annotations.Max.value", "100");

		Assert.assertEquals("$.response.members[1].name", "booleanValue");
		Assert.assertEquals("$.response.members[1].annotations.Digits.fraction", "1");
		Assert.assertEquals("$.response.members[1].annotations.Digits.integer", "10");
		
		Assert.assertEquals("$.response.members[2].name", "list");
		Assert.assertEquals("$.response.members[2].annotations.List.0.Digits.fraction", "3");
		Assert.assertEquals("$.response.members[2].annotations.List.1.Digits.fraction", "5");

	}

}

package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class GenericTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void generic1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest1"));
		Assert.assertNotNull("$.response");
		Assert.assertEquals("$.response.rawType", "com.freestrings.shreder.type.ParameteredTypeOne");
		Assert.assertEquals("$.response.members[0].name", "t");
		Assert.assertEquals("$.response.members[0].type", "string");
		Assert.assertEquals("$.response.members[0].rawType", "java.lang.String");
	}

	@Test
	public void generic2() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest2"));
		Assert.assertEquals("$.response.rawType", "com.freestrings.shreder.type.ParameteredTypeTwo");
		Assert.assertEquals("$.response.members[0].name", "a");
		Assert.assertEquals("$.response.members[1].name", "b");
		Assert.assertEquals("$.response.members[1].members[0].name", "t");
	}

	@Test
	public void generic3() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest3"));
		Assert.assertEquals("$.response.members[1].members[0].type", "integer");
	}

	@Test
	public void generic4() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest4"));
		Assert.assertEquals("$.response.members[0].members[0].type", "string");
		Assert.assertEquals("$.response.members[1].members[0].type", "integer");
	}

	@Test
	public void generic5() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest5"));
		Assert.assertEquals("$.response.type", "array");
		Assert.assertEquals("$.response.members[0].members[0].members[0].type", "string");
		Assert.assertEquals("$.response.members[0].members[1].members[0].type", "integer");
	}

	@Test
	public void generic6() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest6"));
		Assert.assertEquals("$.response.type", "enum");
		Assert.assertEquals("$.response.members[0].name", "TYPE1");
		Assert.assertEquals("$.response.members[1].name", "TYPE2");
	}

	@Test
	public void generic7() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest7"));
		Assert.assertEquals("$.response.type", "enum");
		Assert.assertEquals("$.response.members[0].name", "E1");
		Assert.assertEquals("$.response.members[1].name", "E2");
	}

	@Test
	public void generic8() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest8"));
		Assert.assertEquals("$.response.type", "array");
		Assert.assertEquals("$.response.members[0].members[0].type", "string");
	}

	@Test
	public void generic9() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest9"));
		Assert.assertEquals("$.response.type", "map");
		Assert.assertEquals("$.response.rawType", "org.springframework.util.MultiValueMap");
		Assert.assertEquals("$.response.members[0].name", "$key");
		Assert.assertEquals("$.response.members[1].name", "$value");
	}

	@Test
	public void generic10() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/GenericTest10"));
		Assert.assertEquals("$.response.members[0].members[0].type", "map");
	}

}

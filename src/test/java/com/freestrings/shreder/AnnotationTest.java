package com.freestrings.shreder;

import org.junit.Test;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class AnnotationTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void annotation1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AnnotationTest1"));
		Assert.assertNotNull("$.request.members[0].annotations.RequestParam");
		Assert.assertEquals("$.request.members[0].annotations.RequestParam.defaultValue", "false");
		Assert.assertEquals("$.request.members[0].annotations.RequestParam.required", false);
		Assert.assertNotDefined("$.request.members[0].optionalAnnotations");
	}
	
	@Test
	public void annotation2() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AnnotationTest2"));
		Assert.assertEquals("$.request.members[1].optionalAnnotations.MatrixVariable.value", "foo");
	}
	
	@Test
	public void annotation3() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AnnotationTest3"));
		Assert.assertEquals("$.request.members[0].members[1].optionalAnnotations.AnnotationTest.value", "E2");
	}
	
	@Test
	public void annotation4() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/AnnotationTest4"));
		Assert.assertEquals("$.request.members[0].members[0].optionalAnnotations.AnnotationTest.value", "E1");
	}
}

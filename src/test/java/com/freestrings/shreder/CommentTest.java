package com.freestrings.shreder;

import org.junit.Test;
import org.junit.internal.matchers.StringContains;

import com.jayway.jsonassert.JsonAssert;
import com.jayway.jsonassert.JsonAsserter;

public class CommentTest extends SpringRequestMappingDocumentProcessorTestBase {

	@Test
	public void comment1() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CommentTest1"));
		Assert.assertThat("$.comment", new StringContains("method:commenttest1"));
		Assert.assertThat("$.response.comment", new StringContains("class:ParameteredTypeOne<T>"));
		Assert.assertThat("$.response.members[0].comment", new StringContains("class:ParameteredTypeOne<T>"));
		Assert.assertThat("$.response.members[0].members[0].comment", new StringContains("method:getT"));
	}

	@Test
	public void comment2() throws Exception {
		JsonAsserter Assert = JsonAssert.with(run("com/freestrings/shreder/CommentTest2"));
		Assert.assertThat("$.response.members[0].comment", new StringContains("field comment list"));
	}
}

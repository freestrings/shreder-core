package com.freestrings.shreder;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AnnotationTest.class, CircularRefTest.class, CommentTest.class, GenericTest.class, InheritTest.class, ValidationTest.class,
		AsyncTest.class })
public class AllTests {

}

package com.freestrings.shreder.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationTest {

	MyEnum value() default MyEnum.E2;
	
	String option() default "";

	public enum MyEnum {
		E1, E2
	}
}

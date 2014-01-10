package com.freestrings.shreder.type;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.ValueConstants;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MatrixVariable {

	/**
	 * The name of the matrix variable.
	 */
	String value() default "";

	/**
	 * The name of the URI path variable where the matrix variable is located,
	 * if necessary for disambiguation (e.g. a matrix variable with the same
	 * name present in more than one path segment).
	 */
	String pathVar() default ValueConstants.DEFAULT_NONE;

	/**
	 * Whether the matrix variable is required.
	 * <p>Default is {@code true}, leading to an exception thrown in case
	 * of the variable missing in the request. Switch this to {@code false}
	 * if you prefer a {@code null} in case of the variable missing.
	 * <p>Alternatively, provide a {@link #defaultValue() defaultValue},
	 * which implicitly sets this flag to {@code false}.
	 */
	boolean required() default true;

	/**
	 * The default value to use as a fallback. Supplying a default value implicitly
	 * sets {@link #required()} to false.
	 */
	String defaultValue() default ValueConstants.DEFAULT_NONE;

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.MatrixVariable;

@Controller
public class AnnotationTest2 {

	@RequestMapping("annotationtest2")
	public @ResponseBody
	String test(@PathVariable String path1, @MatrixVariable(value="foo", pathVar="path1") String foo1,
			@PathVariable String path2, @MatrixVariable(value="foo", pathVar="path2") String foo2) {
		return null;
	}
}

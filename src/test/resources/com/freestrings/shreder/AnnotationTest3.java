package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeOne;

@Controller
public class AnnotationTest3 {

	@RequestMapping("annotationtest3")
	public @ResponseBody
	String test(ParameteredTypeOne<String> value) {
		return null;
	}
}

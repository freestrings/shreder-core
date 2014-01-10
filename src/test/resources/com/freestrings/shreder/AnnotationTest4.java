package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeThree;

@Controller
public class AnnotationTest4 {

	@RequestMapping("annotationtest4")
	public @ResponseBody
	String test(ParameteredTypeThree value) {
		return null;
	}
}

package com.freestrings.shreder;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AnnotationTest1 {

	@RequestMapping("annotationtest1")
	public @ResponseBody
	String test(@RequestParam(defaultValue = "false", required = false) boolean value, Principal p, @RequestParam MultipartFile file) {
		return null;
	}
}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ValidationType;

@Controller
public class ValidationTest1 {

	@RequestMapping("validationtest")
	public @ResponseBody
	ValidationType test() {
		return null;
	}
}

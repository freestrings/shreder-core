package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GenericTest7 {
	
	enum TestEnum {
		E1, E2
	}
	
	@RequestMapping("generictest7")
	public @ResponseBody
	TestEnum test() {
		return null;
	}

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.TestEnum;

@Controller
public class GenericTest6 {
	
	@RequestMapping("generictest6")
	public @ResponseBody
	TestEnum test() {
		return null;
	}

}

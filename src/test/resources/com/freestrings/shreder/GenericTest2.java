package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeOne;
import com.freestrings.shreder.type.ParameteredTypeTwo;

@Controller
public class GenericTest2 {

	@RequestMapping("generictest2")
	public @ResponseBody
	ParameteredTypeTwo<String, ParameteredTypeOne> test() {
		return null;
	}
	
}

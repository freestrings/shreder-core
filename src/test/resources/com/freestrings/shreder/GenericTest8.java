package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeOne;

@Controller
public class GenericTest8 {
	
	@RequestMapping("generictest8")
	public @ResponseBody
	ParameteredTypeOne<String> [] test() {
		return null;
	}

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GenericTest9 {
	
	@RequestMapping("generictest9")
	public @ResponseBody
	MultiValueMap<String, String> test() {
		return null;
	}

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.MapTest;

@Controller
public class GenericTest10 {
	
	@RequestMapping("generictest10")
	public @ResponseBody
	MapTest test() {
		return null;
	}

}

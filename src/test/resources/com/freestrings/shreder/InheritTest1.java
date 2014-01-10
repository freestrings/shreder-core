package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.Child;

@Controller
public class InheritTest1 {

	@RequestMapping("inherit1")
	public @ResponseBody
	Child test() {
		return null;
	}

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.Child2;

@Controller
public class InheritTest2 {

	@RequestMapping("inherit2")
	public @ResponseBody
	Child2 test() {
		return null;
	}

}

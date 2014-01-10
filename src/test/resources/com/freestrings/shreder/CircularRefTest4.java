package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.CircularRefTypeList;

@Controller
public class CircularRefTest4 {
	
	@RequestMapping("circlereftest4")
	public @ResponseBody
	CircularRefTypeList test() {
		return null;
	}

}

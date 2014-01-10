package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.CircularRefTypeOne;

@Controller
public class CircularRefTest1 {
	
	@RequestMapping("circlereftest1")
	public @ResponseBody
	CircularRefTypeOne test() {
		return null;
	}

}

package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.CircularRefTypeTwo;
import com.freestrings.shreder.type.ParameteredTypeOne;

@Controller
public class CircularRefTest2 {
	
	@RequestMapping("circlereftest2")
	public @ResponseBody
	CircularRefTypeTwo<CircularRefTypeTwo<ParameteredTypeOne<String>>> test() {
		return null;
	}

}

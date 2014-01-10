package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.CircularRefTypeThree;

@Controller
public class CircularRefTest3 {
	
	@RequestMapping("circlereftest3")
	public @ResponseBody
	CircularRefTypeThree test() {
		return null;
	}

}

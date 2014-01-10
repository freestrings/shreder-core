package com.freestrings.shreder;

import java.math.BigDecimal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TypeTest1 {

	@RequestMapping("typetest")
	public @ResponseBody
	BigDecimal test() {
		return null;
	}

}

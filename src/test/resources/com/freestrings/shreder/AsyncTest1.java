package com.freestrings.shreder;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AsyncTest1 {

	@RequestMapping("asynctest1")
	public @ResponseBody
	Callable<String> test() {
		return null;
	}
}

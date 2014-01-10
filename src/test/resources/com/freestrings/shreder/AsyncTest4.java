package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class AsyncTest4 {

	@RequestMapping("asynctest4")
	public @ResponseBody
	DeferredResult<String> test() {
		return null;
	}
}

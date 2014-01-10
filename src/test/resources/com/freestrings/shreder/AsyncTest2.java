package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

@Controller
public class AsyncTest2 {

	@RequestMapping("asynctest2")
	public @ResponseBody
	WebAsyncTask<String> test() {
		return null;
	}
}

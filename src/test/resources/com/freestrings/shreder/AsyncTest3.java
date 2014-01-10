package com.freestrings.shreder;

import java.util.concurrent.Callable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.WebAsyncTask;

@Controller
public class AsyncTest3 {

	@RequestMapping("asynctest3")
	public @ResponseBody
	WebAsyncTask<Callable<String>> test() {
		return null;
	}
}

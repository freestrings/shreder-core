package com.freestrings.shreder;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeOne;
import com.freestrings.shreder.type.ParameteredTypeTwo;

@Controller
@RequestMapping("/test")
public class TestController {
	/**
	 * 
	 * @return
	 */
	public
	@RequestMapping("/1")
	@ResponseBody
	ParameteredTypeOne<String> getMethod1(@PathVariable(value="1") @Valid String a, ParameteredTypeOne<Integer> b) {
		return null;
	}

	@RequestMapping("/2")
	ParameteredTypeTwo<String, Integer> getMethod2() {
		return null;
	}

	@RequestMapping("/3")
	ParameteredTypeTwo<ParameteredTypeOne<String>, Integer> getMethod3() {
		return null;
	}

	@RequestMapping("/4")
	List<String> getMethod4() {
		return null;
	}

	@RequestMapping("/5")
	List<ParameteredTypeOne<String>> getMethod5() {
		return null;
	}

	@RequestMapping("/6")
	String getMethod6() {
		return null;
	}
}

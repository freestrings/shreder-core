package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.freestrings.shreder.type.ParameteredTypeOne;
import com.freestrings.shreder.type.ParameteredTypeTwo;

@Controller
public class GenericTest5 {

	@RequestMapping("generictest5")
	public @ResponseBody
	java.util.List<ParameteredTypeTwo<ParameteredTypeOne<String>, ParameteredTypeOne<Integer>>> test() {
		return null;
	}

}

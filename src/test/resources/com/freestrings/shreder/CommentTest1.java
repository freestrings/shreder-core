package com.freestrings.shreder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * class:CommentTest1
 * 
 * @author freestrings
 * 
 */
@Controller
public class CommentTest1 {
	
	
	/**
	 * 
	 * class:ParameteredTypeOne<T>
	 * 
	 * @author freestrings
	 * 
	 */
	class ParameteredTypeOne<T> {
		
		/**
		 * method:getT
		 * @return 
		 */
		public T getT() {
			return null;
		}
	}

	/**
	 * method:commenttest1
	 * 
	 * @return
	 */
	@RequestMapping("commenttest1")
	public 
	@ResponseBody
	ParameteredTypeOne<ParameteredTypeOne<String>> getComment() {
		return null;
	}
}

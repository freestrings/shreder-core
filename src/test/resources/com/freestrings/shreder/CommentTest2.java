package com.freestrings.shreder;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * class:CommentTest2
 * 
 * @author freestrings
 * 
 */
@Controller
public class CommentTest2 {

	class Type2 {

		/**
		 * field comment list
		 */
		private List<String> list;

		public List<String> getList() {
			return list;
		}

		public void setList(List<String> list) {
			this.list = list;
		}

	}

	/**
	 * method:commenttest1
	 * 
	 * @return
	 */
	@RequestMapping("commenttest2")
	public @ResponseBody
	Type2 getComment() {
		return null;
	}
}

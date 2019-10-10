/**
 * IndexController.java
 * Created on 10 de out de 2019
 * 
 *
 */

package br.com.art.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * Description the class IndexController.java
 *
 * @Autor daniela.conceicao
 * @since
 * @version %I%, %G%
 */
@Controller	
public class IndexController {
	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "HowToDoInJava Reader !!");
		return "index";
	}

	@RequestMapping("/next")
	public String next(Map<String, Object> model) {
		model.put("message", "You are in new page !!");
		return "next";
	}
}

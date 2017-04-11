package com.yqz.springboot.quickstart.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * supported by BeanNameUrlHandlerMapping
 * 
 * @author Administrator
 *
 */

@Controller("/s")
public class SimpleController extends AbstractController {

	public SimpleController() {
		setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
		setCacheSeconds(10);
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ModelAndView mv = new ModelAndView("home");
		mv.addObject("msg", "hello, SimpleController");
		return mv;
	}

}
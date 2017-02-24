package com.yqz.springboot.quickstart;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/hello")
public class Hello {
	@RequestMapping(value = "", method = RequestMethod.GET)
	String hello() {
		return "hello!!";
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public MyMessage sayHello(@RequestParam(value = "message", defaultValue = "你好") String msg, Model model) {

		if (msg == null || msg.trim().equals(""))
			return new MyMessage("I say hello.");
		if (model != null && model.asMap().size() > 0)
			return new MyMessage("I say a lot");
		return new MyMessage("I say  " + msg);
	}

	@RequestMapping("date/{date}")
	public String date(@PathVariable Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "input date is " + DateFormat.getDateInstance().format(date);
	}

	@RequestMapping("get/{id}")
	public String getById(@PathVariable int id) {
		return "input id is " + id;
	}

	@InitBinder // 必须有一个参数WebDataBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
		binder.registerCustomEditor(int.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() { // TODO Auto-generated method stub
				return getValue().toString();
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				System.out.println(text + "...........................................");
				setValue(Integer.parseInt(text));
			}

		});
	}

	/*
	 * @ExceptionHandler(CustomGenericException.class) public ModelAndView
	 * handleCustomException(CustomGenericException ex) {
	 * 
	 * ModelAndView model = new ModelAndView("error/generic_error");
	 * model.addObject("errCode", ex.getErrCode()); model.addObject("errMsg",
	 * ex.getErrMsg());
	 * 
	 * return model;
	 * 
	 * }
	 */

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errMsg", "this is Exception.class");

		return model;

	}

	public static class MyMessage {
		public MyMessage(String msg) {
			this.message = msg;
		}

		private String message;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}

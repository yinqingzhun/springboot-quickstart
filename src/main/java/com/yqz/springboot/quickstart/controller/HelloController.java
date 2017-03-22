package com.yqz.springboot.quickstart.controller;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yqz.springboot.quickstart.service.HelloService;

@RestController
@RequestMapping("hello")
public class HelloController extends BaseController {

	@Autowired
	HelloService helloService;

	@Value("${app.description}")
	private String appDes;

	@RequestMapping(value = "", method = RequestMethod.GET)
	String hello() {
		return "hello! I'm " + this.helloService.getAppName() + ". " + this.helloService.getAppInfo();
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public MyMessage sayHello(String msg, Model model) {

		if (msg == null || msg.trim().equals(""))
			return new MyMessage("I say hello.");
		if (model != null && model.asMap().size() > 0)
			return new MyMessage("I say a lot");
		return new MyMessage("I say  " + msg);
	}

	@RequestMapping("date/{date}")
	public String date(@DateTimeFormat(iso = ISO.DATE) @PathVariable Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "input date is " + DateFormat.getDateInstance().format(date);
	}

	@RequestMapping("get/{id}")
	public String getById(@PathVariable int id, @RequestParam(required = false, defaultValue = "0") int version) {
		return "input id is " + id + ".version:" + version;
	}

	@InitBinder // 必须有一个参数WebDataBinder
	public void initializeBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false));
		binder.registerCustomEditor(int.class, new PropertyEditorSupport() {
			@Override
			public String getAsText() {
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
			this(msg, LocalDateTime.now());

		}

		public MyMessage(String msg, LocalDateTime dt) {
			this.message = msg;
		}

		private String message;
		private LocalDateTime createTime;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public LocalDateTime getCreateTime() {
			return createTime;
		}

		public void setCreateTime(LocalDateTime createTime) {
			this.createTime = createTime;
		}

	}
}

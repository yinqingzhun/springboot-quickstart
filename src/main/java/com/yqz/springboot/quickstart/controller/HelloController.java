package com.yqz.springboot.quickstart.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Executors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yqz.springboot.quickstart.exception.UserNotFoundException;
import com.yqz.springboot.quickstart.model.MyMessage;
import com.yqz.springboot.quickstart.service.HelloService;

@Validated
@RestController
@RequestMapping("hello")
public class HelloController extends BaseController {
	Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	HelloService helloService;

	@Value("${app.description}")
	private String appDes;

	@RequestMapping("get/{id}")
	public String getById(@PathVariable @Min(1) int id,
			@RequestParam(required = false, defaultValue = "0") int version) {
		if (id < 10)
			throw new UserNotFoundException(id);
		return "input id is " + id + ".version:" + version;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	String hello() {

		Executors.newFixedThreadPool(1).submit(() -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			logger.debug("new thread exits.");
		});
		return "hello! I'm " + this.helloService.getAppName() + ". " + this.helloService.hello("jack")
				+ this.helloService.welcome("jack");
	}

	@Cacheable(value = "now")
	@RequestMapping(value = "now", method = RequestMethod.GET)
	String now() {
		return LocalDateTime.now().toString();
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@RequestMapping(value = "/sayHello", method = RequestMethod.GET)
	public @Valid MyMessage sayHello(@RequestParam("message") String msg, Model model) {

		if (msg == null || msg.trim().equals(""))
			return new MyMessage("I say hello.");
		if (model != null && model.asMap().size() > 0)
			return new MyMessage("I say a lot");
		return new MyMessage("I say  " + msg);
	}

	@RequestMapping(value = "/showMessage", method = RequestMethod.GET)
	public @Valid MyMessage showMessage(@Valid MyMessage msg) {
		return msg;
	}

	@RequestMapping("date/{date}")
	public String date(@DateTimeFormat(iso = ISO.DATE) @PathVariable Date date) {
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "input date is " + DateFormat.getDateInstance().format(date);
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

}
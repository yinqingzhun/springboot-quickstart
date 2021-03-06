package com.yqz.springboot.quickstart.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yqz.springboot.quickstart.exception.UserNotFoundException;
import com.yqz.springboot.quickstart.model.MyMessage;
import com.yqz.springboot.quickstart.model.ReturnValue;
import com.yqz.springboot.quickstart.service.HelloService;

@Validated
@Controller
@RequestMapping("hi")
public class HiController {
    Logger logger = LoggerFactory.getLogger(HiController.class);
    @Autowired
    HelloService helloService;

    @Value("${app.description}")
    private String appDes;

    @RequestMapping
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "h5";
    }

    @ResponseBody
    @RequestMapping("get/{id}")
    public String getById(@PathVariable @Max(100000) int id,
                          @RequestParam(required = false, defaultValue = "0") int version) {
        if (id <= 0)
            throw new UserNotFoundException(id);
        else if (id > 10000)
            throw new IllegalArgumentException("id could not be greater then 10000");
        return "input id is " + id + ".version:" + version;
    }

    @ResponseBody
    @RequestMapping(value = "hello", method = RequestMethod.GET)
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

    @ResponseBody
    @Cacheable(value = "now")
    @RequestMapping(value = "now", method = RequestMethod.GET)
    public String now() {
        return LocalDateTime.now().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public @Valid MyMessage sayHello(@RequestParam("message") String msg, Model model) {

        if (msg == null || msg.trim().equals(""))
            return new MyMessage("I say hello.");
        if (model != null && model.asMap().size() > 0)
            return new MyMessage("I say a lot");
        return new MyMessage("I say  " + msg);
    }

    @ResponseBody
    @RequestMapping(value = "/showMessage", method = RequestMethod.POST)
    public MyMessage showMessage(@RequestParam("info") String info, @RequestBody MyMessage msg, BindingResult br) {
        if (br.hasErrors())
            return new MyMessage() {
                @Override
                public String getMessage() {
                    try {
                        return new ObjectMapper().writeValueAsString(br.getAllErrors());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            };
        return msg;
    }

    @ResponseBody
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    public MyMessage addMessage(@RequestBody MyMessage msg) {
        return msg;
    }

    @ResponseBody
    @PostMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) {
        return;
    }

    @ResponseBody
    @RequestMapping("date/{date}")
    public String date(@DateTimeFormat(iso = ISO.DATE) @PathVariable Date date) {
        DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "input date is " + DateFormat.getDateInstance().format(date);
    }

    @ResponseBody
    @RequestMapping(value = "welcome")
    public ReturnValue welcome(HttpServletRequest request) {
        Principal p = request.getUserPrincipal();
        return ReturnValue.buildSuccessResult(p);
    }

    @ResponseBody
    @RequestMapping(value = "ex", method = RequestMethod.GET)
    public MyMessage ex() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("计算失败", e);
            return new MyMessage("exception occur." + e.toString());
        }
        return new MyMessage("everything is ok.");

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

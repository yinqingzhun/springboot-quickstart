package com.yqz.springboot.quickstart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yqz.springboot.quickstart.service.AliService;

@RestController
@RequestMapping("ali")
public class AliController {
	@Autowired
	AliService aliService;

	@RequestMapping(value = "tk", method = { RequestMethod.POST })
	public void callTicket(HttpServletRequest request, HttpServletResponse response) {
		aliService.callTicket(request, response);
	}
}

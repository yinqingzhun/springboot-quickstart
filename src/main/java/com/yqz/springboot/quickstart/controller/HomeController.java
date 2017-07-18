package com.yqz.springboot.quickstart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yqz.springboot.quickstart.model.Account;
import com.yqz.springboot.quickstart.model.MyMessage;

@SuppressWarnings("unused")
@Controller
@RequestMapping(value = "/h")
public class HomeController {
	
	
	@ModelAttribute("myMessage")
	MyMessage getMyMessage() {
		return new MyMessage("welcome,buddy!");
	}

	@RequestMapping
	public String home(@ModelAttribute("myMessage") MyMessage message, Model model) {
		message.setMessage("hi,buddy!");
		model.addAttribute("hello", "this is home method in HomeController.");
		model.addAttribute("url","http://www.baidu.com?search=xx&order=0");
		return "h5";
	}

	@RequestMapping(value = { "/index" })
	public String index(RedirectAttributes redirectAttrs, Model model) {    
		model.addAttribute("hello", "hello,everyone!!");
		return "home";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public String handle(Account account, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			return "accounts/new";
		}

		// Save account ...
		redirectAttrs.addAttribute("id", account.getId()).addFlashAttribute("message", "Account created!");
		return "redirect:/accounts/{id}";
	}

	@RequestMapping(value = "/rb", method = RequestMethod.GET)
	public String resourceBundleViewResolver() {
		return "rb";
	}
}

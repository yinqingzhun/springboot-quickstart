package com.yqz.springboot.quickstart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yqz.springboot.quickstart.model.Account;
import com.yqz.springboot.quickstart.model.MyMessage;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@ModelAttribute("myMessage")
	MyMessage getMyMessage() {
		return new MyMessage("welcome,buddy!");
	}

	@RequestMapping(value = "")
	public String home(@ModelAttribute("myMessage") MyMessage message) {
		message.setMessage("hi,buddy!");
		return "home";
	}

	@RequestMapping(value = "/index")
	public String index(RedirectAttributes redirectAttrs) {
		return "forward:/home";
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

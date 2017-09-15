package com.yqz.springboot.quickstart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yqz.springboot.quickstart.model.Account;
import com.yqz.springboot.quickstart.model.MyMessage;
import com.yqz.springboot.quickstart.model.Person;
import com.yqz.springboot.quickstart.model.ReturnValue;

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
		model.addAttribute("url", "http://www.baidu.com?search=xx&order=0");
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

	@GetMapping("addPerson")
	public String addPerson(@RequestParam(value = "id", required = false, defaultValue = "") Integer id) {

		return "addPerson";
	}

	@RequestMapping(value = "addPerson", method = RequestMethod.POST)
	@ResponseBody
	public ReturnValue addPerson(@ModelAttribute @Valid Person person, BindingResult bind, Model model) {
		if (bind.hasFieldErrors()) {
			String message = bind.getFieldError().getDefaultMessage();
			return ReturnValue.buildErrorResult(101, message);
		}

		return ReturnValue.buildSuccessResult(null);
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}
}

package com.yqz.springboot.quickstart.config;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.yqz.springboot.quickstart.exception.UserNotFoundException;

@ControllerAdvice
public class MvcControllerAdvice {
	Logger logger = LoggerFactory.getLogger(MvcControllerAdvice.class);

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	VndErrors userNotFoundExceptionHandler(UserNotFoundException ex) {
		logger.error("found UserNotFoundException");
		return new VndErrors("error", ex.getMessage());
	}

	@InitBinder // 必须有一个参数WebDataBinder
	public void initializeBinder(WebDataBinder binder, WebRequest req) {
		binder.registerCustomEditor(Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false));
		binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
			private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			@Override
			public String getAsText() {
				LocalDateTime dt = (LocalDateTime) getValue();
				if (dt == null)
					return "";
				return dt.format(formatter);
			}

			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				try {
					setValue(LocalDateTime.parse(text, formatter));
				} catch (Exception e) {

				}

			}
		});

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
	 * @Bean public MethodValidationPostProcessor
	 * methodValidationPostProcessor() { return new
	 * MethodValidationPostProcessor(); }
	 */
}

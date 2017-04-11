package com.yqz.springboot.quickstart.handler;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestHandlerExceptionResolver extends AbstractHandlerMethodExceptionResolver {
	private final static Logger logger = org.slf4j.LoggerFactory.getLogger(RestHandlerExceptionResolver.class);

	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception ex) {

		if (request.getHeader("accept").toLowerCase().contains("application/json")) {

		} else if (request.getHeader("accept").toLowerCase().contains("application/xml")) {

		}

		if (ex != null && (AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), ResponseBody.class) != null
				|| AnnotationUtils.findAnnotation(handlerMethod.getMethod(), ResponseBody.class) != null)) {
			try {
				String message = "";
				if (StringUtils.hasText(ex.getMessage()))
					message = ex.getMessage();

				HashMap<String, String> map = new HashMap<>();

				if (ex instanceof ConstraintViolationException)
					map.put("message", "参数值不合法");
				else {
					map.put("message", ex.getClass().getSimpleName() + " occoured." + message);
					map.put("detail", ex.toString());
				}
				response.setCharacterEncoding("utf-8");
				response.getWriter().append(new ObjectMapper().writeValueAsString(map)).flush();
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}

		return new ModelAndView();

	}

}

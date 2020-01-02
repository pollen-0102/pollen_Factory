package com.oracle.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
			Exception e) {
		    ModelAndView mv = new ModelAndView();
			String message = e.getMessage();
			mv.addObject("message", message);
			mv.setViewName("error");
		return mv;
	}

}

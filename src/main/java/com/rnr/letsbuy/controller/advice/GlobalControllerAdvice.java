package com.rnr.letsbuy.controller.advice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.rnr.letsbuy.configuration.AppConfiguration;

/*
 * (basePackages={"con.rnr.letsbuy.controller"}) there's no need to declare it, as spring boot scans every @Component.
 * As a reminder, @ControllerAdvice is also a @Component.
 */
@ControllerAdvice
/*
 * Minimum values are the highest int HIGHEST_PRECEDENCE = Integer.MIN_VALUE;
 * int LOWEST_PRECEDENCE = Integer.MAX_VALUE;
 * 
 * As the purpose of this controller advice will be the most generic, we
 * establish a non trivial value to be the lowest precedence in this kind of
 * component
 */
@Order(1000)
public class GlobalControllerAdvice {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private AppConfiguration configuration;

	@ModelAttribute
	public void loadAttributes(Model model) {
		model.addAttribute("formatDate", configuration.getDateFormat());
	}

	/*
	 * If no exception is specified in the annotation will face to: Caused by:
	 * java.lang.IllegalArgumentException: No exception types mapped to {public
	 * void
	 * com.rnr.letsbuy.controller.advice.GlobalControllerAdvice.handleException
	 * 
	 */
	@ExceptionHandler({ Exception.class, RuntimeException.class })
	public String handleException(HttpServletRequest request, Exception exception, Model model) {
		log.error(">> Error while serving " + request.getPathInfo());
		log.error(">> Exception :", exception);
		return "/customError";
	}

	@Autowired
	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}

}

package com.rnr.letsbuy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.rnr.letsbuy.configuration.AppConfiguration;

/*
 * https://stackoverflow.com/questions/38935873/what-is-difference-between-handlerinterceptor-and-handlerinceptoradaptor-in-spri
 *	
 * As the resource states, implementing the HandlerInterceptor interfaces makes us to write boilerplate code if we don't really need 
 * to implement more than one method. That's the reason why we finally replaced the HandlerInterceptor by the abstract class HandlerInterceptorAdapter
 */
public class CustomInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AppConfiguration configuration;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String controllerName = "";
		String actionName = "";
		log.debug("Pre-intercepting request");
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			controllerName = hm.getBeanType().getSimpleName().replace("Controller", "");
			actionName = hm.getMethod().getName();

		}
		modelAndView.addObject("formatDate", configuration.getDateFormat());
		modelAndView.addObject("controllerName", controllerName);
		modelAndView.addObject("actionName", actionName);

		/*
		 * This will come in handy to distinguish between a creation o
		 * modification of a given item (Customer, Product, Review, etc)
		 */
		if (StringUtils.containsIgnoreCase(actionName, "edit")) {
			modelAndView.addObject("formStatus", "edit");
		}
		if (StringUtils.containsIgnoreCase(actionName, "create")) {
			modelAndView.addObject("formStatus", "create");
		}
		// log.debug(request.getQueryString());
		// log.debug(request.getPathInfo());
		// log.debug(handler.toString());
		// Enumeration<String> en = request.getAttributeNames();
		// while (en.hasMoreElements()) {
		// final String elName = en.nextElement();
		// log.debug(elName + " : " + request.getAttribute(elName));
		// }
	}

}

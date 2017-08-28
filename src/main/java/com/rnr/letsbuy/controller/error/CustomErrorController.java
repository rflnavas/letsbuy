package com.rnr.letsbuy.controller.error;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * This controller will be used to display error messages info if something went wrong
 * @author rafael.navas.ruiz
 *
 */
@Controller
public class CustomErrorController implements ErrorController {

	private static final String TIMESTAMP = "timestamp";
	private static final String STATUS = "status";
	private static final String MSG = "message";
	private static final String PATH = "path";
	private static final String EXCEPTION = "exception";

	private static final String ERR_PATH = "/error";
	private static final String ERROR_TEMPLATE = "/customError";
	private final ErrorAttributes errorAttributes;

	@Autowired
	public CustomErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping(path = ERR_PATH)
	public String handleError(Model model, HttpServletRequest request) {
		Map<String, Object> errAttr = getErrorAttributes(request, true);

		System.out.println("####################################");
		errAttr.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
		System.out.println("####################################");

		Date timestamp = (Date) errAttr.get(TIMESTAMP);
		model.addAttribute(TIMESTAMP, timestamp);
		model.addAttribute(STATUS, errAttr.get(STATUS));
		model.addAttribute(MSG, errAttr.get(MSG));
		model.addAttribute(PATH, errAttr.get(PATH));
		model.addAttribute(EXCEPTION, errAttr.get(EXCEPTION));
		
		return ERROR_TEMPLATE;
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request,boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		Map<String, Object> errAttr = this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
		return errAttr;
	}
	
	/**
	 * By default, Spring does not know how to handle a 404 error code. 
	 * {@link com.rnr.letsbuy.LetsbuyApplication}
	 */
	@RequestMapping("/404")
	public String pageNotFound(Model model, HttpServletRequest request){
		model.addAttribute("error", getErrorAttributes(request, true));
		return "404";
		
	}

	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}

}

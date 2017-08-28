package com.rnr.letsbuy.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

public class SpringLogUtils {

	private static final Logger log = LoggerFactory.getLogger(SpringLogUtils.class);
	public SpringLogUtils() {
	}
	
	public static void logFlashAttributes(HttpServletRequest request, Model model){
		
		final String infoControllerAction = String.format( "%s.%s", model.asMap().get("controllerName") , model.asMap().get("actionName"));
		
		Map<String, ?> flsMap = RequestContextUtils.getInputFlashMap(request);
		if(flsMap != null){
			log.debug("FlashMap retrieved in " + infoControllerAction);
			flsMap.entrySet().stream().forEach(e -> log.debug(e.getKey() + " - " + e.getValue()));
		} else{
			log.debug("No flashMap found in " + infoControllerAction);
		}
	}
}

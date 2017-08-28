package com.rnr.letsbuy;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;


@SpringBootApplication
public class LetsbuyApplication {

	private final static Logger log = LoggerFactory.getLogger(LetsbuyApplication.class);
	
	public static void main(String[] args) {
		
		
		ApplicationContext ctx = 
				SpringApplication.run(LetsbuyApplication.class, args);
		log.info("************* Started up Letsbuy app! ************* ");
		listBeans(ctx);
		
	}
	
	private static void listBeans(ApplicationContext ctx) {
		List<String> defNames = Arrays.asList(ctx.getBeanDefinitionNames());
		log.debug("These beans have been found");
		defNames.stream().sorted().forEach((b)-> log.info(">> " + b)); 
//		defNames.stream()
//			.filter(b -> b.equalsIgnoreCase("ReviewRestController"))
//			.findFirst().ifPresent((b)-> log.debug(">>>>>" + b + " ------"));
	}
	

	//It does not work
//	@Bean
//	public ReloadableResourceBundleMessageSource messageSource(){
//		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//		messageSource.setBasename("i18n/messages");
//		/*
//		https://stackoverflow.com/questions/39685399/reloadableresourcebundlemessagesource-vs-resourcebundlemessagesource-cache-con
//			0 check file by its last timestamp (last modified date). Avoid it in production
//			-1 will always cache
//			Positive values means that every N seconds, messageSource will examine the last modified date in file.
//		 */
//		messageSource.setUseCodeAsDefaultMessage(true);
//		messageSource.setCacheSeconds(0);
//		return messageSource;
//	}

	/**
	 * HANDLING DIFFERENT KIND OF ERRORS THROUGH CONFIGURING EMBEDDED SERVLET CONTAINERS.
	 * As the following code shows, if a resource couldn't be found, we inform the path 
	 * that must be used to be redirected. We cannot forget to include an error controller 
	 * to handle such path.
	 * @see {@link com.rnr.letsbuy.controller.error.CustomErrorController}
	 */
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer(){
		return (container -> {
			ErrorPage custom404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
			container.addErrorPages(custom404Page);
		});
	}
	
}

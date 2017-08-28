package com.rnr.letsbuy.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.rnr.letsbuy.interceptor.CustomInterceptor;

/**
 * 
 * @author rafael.navas.ruiz
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	/*
	 * We do not use @Autowired property here (It does not work). Instead, we
	 * take the @Autowired setter approach. Reason is commented below
	 */
	private CustomInterceptor customInterceptor;
	private LocaleChangeInterceptor localeChangeInterceptor;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * https://sdqali.in/blog/2016/06/28/injecting-dependencies-into-a-spring-
	 * configuration/
	 * 
	 * VERY IMPORTANT TO NOTE
	 * 
	 * @Configuration is meta-annotated with @Component,
	 * therefore @Configuration classes are candidates for component scanning
	 * (typically using Spring XML's <context:component-scan/> element) and
	 * THEREFORE MAY ALSO TAKE ADVANTAGE OF @AUTOWIRED/@INJECT like any
	 * regular @Component.
	 */
	@Autowired
	public void setCustomInterceptor(CustomInterceptor customInterceptor) {
		this.customInterceptor = customInterceptor;
	}

	@Autowired
	public void setLocaleChangeInterceptor(LocaleChangeInterceptor localeChangeInterceptor) {
		this.localeChangeInterceptor = localeChangeInterceptor;
	}

	@Bean
	public CustomInterceptor customInterceptor() {
		return new CustomInterceptor();
	}

	/**
	 * Add controller for login without needing creating a 'standard' MVC
	 * controller as doc says about its parent.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("auth/login");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	//http://o7planning.org/en/11227/create-a-multiple-languages-web-application-with-spring-mvc
	// Creating the 'messageSource' named bean
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		/*
		 * By default, Spring will try to find messages_XX.properties under
		 * src/main/resources. To change such location we need to call
		 * setBasenames. This indicates that all messagesXXX will be under i18n
		 * folder(NOT i18n/messages/messages_XXXX)
		 */
		source.setBasenames("i18n/messages");
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding(StandardCharsets.UTF_8.name());
		log.info("Setting message source " + source.toString());
		return source;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(new Locale("ES"));
		return slr;
	}

	// Configuring an interceptor that is responsible or swapping out the
	// current locale
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		log.info("Setting LocaleChangeInterceptor " + lci.toString());
		return lci;
	}

	// We must register them so as to make Spring be aware of our interceptors
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(customInterceptor).addPathPatterns("/**");
		/*
		 * If we do not add path patterns, it will be as though as we
		 * specificied addPathPatterns("/**"). In other words, it will be
		 * intercepted in every request.
		 */
		registry.addInterceptor(localeChangeInterceptor);
		super.addInterceptors(registry);
		log.debug("Added interceptors : " + registry.toString());
	}

	// @Bean
	// public SpringTemplateEngine templateEngine() {
	// final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	//
	// Set<IDialect> dialects = new LinkedHashSet<>();
	// dialects.add(new SpringSecurityDialect());
	// /* If we only include the previous Dialect we will face this error
	// message
	// * Caused by: org.thymeleaf.exceptions.ConfigurationException:
	// * When using SpringTemplateEngine, at least one of the configured
	// dialects
	// * must be or extend
	// * org.thymeleaf.spring4.dialect.SpringStandardDialect.
	// */
	// dialects.add(new SpringStandardDialect());
	// templateEngine.setDialects(dialects);
	// templateEngine.addTemplateResolver(templateResolver());
	// return templateEngine;
	// }
	//
	// @Bean
	// public TemplateResolver templateResolver() {
	// TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	// templateResolver.setPrefix("classpath:templates");
	// templateResolver.setSuffix(".html");
	// templateResolver.setTemplateMode("HTML5");
	// // templateResolver.setOrder(1);
	// templateResolver.setCacheable(false);
	// return templateResolver;
	// }
}

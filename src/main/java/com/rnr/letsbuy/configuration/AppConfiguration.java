package com.rnr.letsbuy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author rafael.navas.ruiz
 *
 */
@Component
@ConfigurationProperties(prefix = "config")
public class AppConfiguration {
	
	/*
	 * Setting prefix in @ConfigurationProperties will make Spring to look up in *.yaml or *.properties to find 
	 * a prefixed property. Example for YAML: 
	 * config:
	 * 	 date-format: 'dd/MM/yyyy'
	 */
	private String dateFormat;

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

}

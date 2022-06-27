package com.esanchezd.urlsln.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esanchezd.urlsln.component.KafkaComponent;
import com.esanchezd.urlsln.service.UrlslnService;

@RestController(value = "/")
public class UrlslnController {

    private static final Logger logger = LoggerFactory.getLogger(UrlslnController.class);

    @Autowired(required = true)
    private UrlslnService urlslnService;
    
    @Autowired(required = true)
    private KafkaComponent kafkaComponent;

    
    @Value("${cloudkafka.topic}")
	private String topic;

	@Value("${cloudkafka.brokers}")
	private String brokers;

	@Value("${cloudkafka.username}")
	private String username;

	@Value("${cloudkafka.password}")
	private String password;    
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void method(@PathVariable("id") String id, HttpServletResponse httpServletResponse, HttpServletRequest request) throws Exception {
		logger.info(request.getRemoteAddr());
		String url = urlslnService.getRedirectUrl(id);
		kafkaComponent.produce("XDDD");
		logger.debug(url);
		httpServletResponse.sendRedirect(url);
	}
	
}

package com.esanchezd.urlsln.controller;


import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esanchezd.urlsln.service.UrlslnService;

@RestController(value = "/")
public class UrlslnController {

    private static final Logger logger = LoggerFactory.getLogger(UrlslnController.class);

    @Autowired(required = true)
    private UrlslnService urlslnService;
    
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void method(@PathVariable("id") String id, HttpServletResponse httpServletResponse) throws Exception {

		logger.debug(id);
		String url = urlslnService.getRedirectUrl(id);
		logger.debug(url);
		httpServletResponse.sendRedirect(url);
	}
	
}

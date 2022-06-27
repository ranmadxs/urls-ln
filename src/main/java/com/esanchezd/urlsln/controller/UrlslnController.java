package com.esanchezd.urlsln.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/")
public class UrlslnController {

    private static final Logger logger = LoggerFactory.getLogger(UrlslnController.class);
	
    //@GetMapping(value="/")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//public void method(@PathVariable("itemid") String itemid, HttpServletResponse httpServletResponse) {
    public void method(@PathVariable("id") String id, HttpServletResponse httpServletResponse) throws IOException {
		logger.info("XD4");
		logger.info(id);
		httpServletResponse.sendRedirect("http://www.google.cl");
		//httpServletResponse.setHeader("Location", projectUrl);
	    //httpServletResponse.setStatus(302);
	}
	
}

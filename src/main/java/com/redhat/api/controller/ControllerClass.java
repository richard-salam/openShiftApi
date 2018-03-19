package com.redhat.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Component
public class ControllerClass {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView goHome() {

		ModelAndView model = new ModelAndView();
		model.setViewName("homePage");

		return model;
	}

	@RequestMapping(value = { "/check" }, method = RequestMethod.GET)
	public ModelAndView goCheck() {

		ModelAndView model = new ModelAndView();
		model.setViewName("check");

		return model;
	}
}

package com.redhat.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.redhat.api.DAOLayer.EntityDAO;
import com.redhat.api.entity.EntityClass;

@Controller
public class ControllerClass {

	@Autowired
	private EntityDAO dao;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView goHome() {

		ModelAndView model = new ModelAndView();
		List<EntityClass> list = dao.listUser();
		model.addObject("list", list);
		model.setViewName("homePage");

		return model;
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView goCheck(@ModelAttribute EntityClass entity, BindingResult bindFailure) {

		if (entity.getId() == 0 && !bindFailure.hasErrors()) {
			dao.addUser(entity);
		} else {

			System.out.println("Can't Add User");
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegisterPage(ModelAndView model) {

		EntityClass entity = new EntityClass();
		model.addObject("entity", entity);
		model.setViewName("register");

		return model;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(Integer id) {

		ModelAndView model = new ModelAndView();
		dao.deleteUser(id);
		model.setViewName("redirect:/");
		
		return model;
	}
	
	@ResponseBody
	@RequestMapping(value="/json", method=RequestMethod.GET)
	public Object showList(){
		
		Map<Object, Object> list = new HashMap<>();
		
		List<EntityClass> entityList = new ArrayList<EntityClass>();
		entityList.addAll(dao.listUser());
		
		list.put("listAll", entityList);
		
		return list;
	}

}

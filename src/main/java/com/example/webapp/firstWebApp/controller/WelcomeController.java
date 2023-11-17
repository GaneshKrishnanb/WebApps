package com.example.webapp.firstWebApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.webapp.firstWebApp.entity.*;
import com.example.webapp.firstWebApp.service.TodoService;
@Controller
public class WelcomeController {
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String redirectToTodoList(ModelMap map) {
		map.addAttribute("name", "name");
	    return "redirect:/list-todo";
	}

	@RequestMapping(value = "/list-todo", method = RequestMethod.GET)
	public String listAllTodo(ModelMap map) {
		List<Todo> todo = TodoService.findByUsername("Udemy");
	    map.addAttribute("todo", todo);
	    return "todo";
	}


}

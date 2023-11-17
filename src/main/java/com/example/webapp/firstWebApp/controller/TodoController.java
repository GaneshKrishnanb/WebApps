package com.example.webapp.firstWebApp.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.webapp.firstWebApp.entity.Todo;
import com.example.webapp.firstWebApp.service.TodoService;


@Controller
@SessionAttributes("name")
public class TodoController {

	TodoService todoService;

	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@RequestMapping(value = "list-to-do", method = RequestMethod.GET)
	public String listAlltodo(ModelMap map){
		List<Todo> todo = todoService.findByUsername("Udemy");
		map.addAttribute("todo", todo);
		return "todo";	
	}
	
	@RequestMapping(value = "add-to-do", method = RequestMethod.GET)
	public String showTodo(ModelMap model){
		String name1 = (String) model.get("name");
		Todo todo = new Todo(0, name1, "", 
		LocalDate.now().plusYears(1), false);
		model.put("todo", todo);
		return "add-todo";	
	}
	@RequestMapping(value = "add-to-do", method = RequestMethod.POST)
	public String addNewTodo(ModelMap map, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "add-todo";
		}
		String name1 = (String) map.get("name");
		TodoService.addtodo(name1, todo.getDescription(), 
		LocalDate.now().plusYears(1), false);
		return "redirect:/list-to-do";//Instead of duplicating the logic of listAlltodo 
	}
	@RequestMapping(value = "delete-todo")
	public String deleteTodo(@RequestParam int id) {
		// TODO Auto-generated method stub
		todoService.deleteByID(id);
		return "redirect:/list-to-do";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.GET)
	public String ShowUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.findByID(id);
		model.addAttribute("todo", todo);
		return "update-todo";
	}
	
	@RequestMapping(value = "update-todo", method = RequestMethod.POST)
	public String UpdateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			return "update-todo";
		}
		Todo todoFromModel = (Todo) model.get("todo");
		//String name2 = (String) model.get("name");
		todoService.updateTodo(todo);
		return "redirect:/list-to-do";
	}
	
}

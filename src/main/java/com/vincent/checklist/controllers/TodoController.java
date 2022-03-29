package com.vincent.checklist.controllers;

import java.util.List;

import com.vincent.checklist.models.Todo;
import com.vincent.checklist.services.TodoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequestMapping("todo")
@RestController
public class TodoController {

	private final TodoService todoService;

	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping()
	public List<Todo> getTodoList() {
		return todoService.getTodoList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable("id") int id) {
		Todo todo = todoService.getTodo(id);
		return todo != null ? new ResponseEntity<>(todo, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping()
	public ResponseEntity<String> storeTodo(@RequestBody Todo todo) {
		return todoService.storeTodo(todo) != null ? new ResponseEntity<>("Saved Successfully", HttpStatus.OK)
				: new ResponseEntity<>("Not Saved Successfully", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> storeTodo(@PathVariable("id") int id, @RequestBody Todo todo) {
		Todo exTodo = todoService.getTodo(id);
		todo.setId(id);
		return exTodo != null ? todoService.storeTodo(todo) != null
				? new ResponseEntity<>("Updated Successfully", HttpStatus.OK)
				: new ResponseEntity<>("Not Updated Successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") int id) {
		Todo exTodo = todoService.getTodo(id);
		return exTodo != null ? todoService.deleteTodo(id)
				? new ResponseEntity<>("Deleted Successfully", HttpStatus.OK)
				: new ResponseEntity<>("Not Deleted Successfully", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}

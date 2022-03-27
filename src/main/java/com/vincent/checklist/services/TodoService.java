package com.vincent.checklist.services;

import java.util.List;

import com.vincent.checklist.models.Todo;

public interface TodoService {

	List<Todo> getTodoList();

	Todo getTodo(int id);

	Todo storeTodo(Todo todo);

	boolean deleteTodo(int id);
}
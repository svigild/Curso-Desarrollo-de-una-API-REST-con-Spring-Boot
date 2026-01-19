package com.openwebinars.todo_rest.service;

import java.util.List;

import com.openwebinars.todo_rest.repos.TaskRepository;
import com.openwebinars.todo_rest.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openwebinars.todo_rest.dto.EditTaskCommand;
import com.openwebinars.todo_rest.error.TaskNotFoundException;
import com.openwebinars.todo_rest.model.Task;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	/**
	 * Obtiene todas las tareas.
	 * 
	 * @return La lista de tareas o lanza error si está vacía.
	 */
	public List<Task> findAll() {
		List<Task> allTasks = taskRepository.findAll();
		
		if (allTasks.isEmpty())
			throw new TaskNotFoundException();
		
		return allTasks;
	}
	
	/**
	 * Obtiene una tarea dado su id.
	 * 
	 * @param id - El identificador de la tarea.
	 * @return La tarea encontrada o lanza un error si no existe.
	 */
	public Task findById(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(id));
	}
	
	/**
	 * Crea una nueva tarea.
	 * 
	 * @param cmd
	 * @return
	 */
	public Task save(EditTaskCommand cmd, User author) {
		return taskRepository.save(
				Task.builder()
					.title(cmd.title())
					.description(cmd.description())
					.deadLine(cmd.deadLine())
						.author(author)
					.build()
				);
	}

	/**
	 * Edita una tarea existente.
	 *
	 * @param cmd
	 * @param id
	 * @return
	 */
	public Task edit(EditTaskCommand cmd, Long id) {
		return taskRepository.findById(id)
				.map(t -> {
					t.setTitle(cmd.title());
					t.setDescription(cmd.description());
					t.setDeadLine(cmd.deadLine());
					return taskRepository.save(t);
				})
				.orElseThrow(() -> new TaskNotFoundException(id));
		
	}

	/**
	 * Elimina una tarea.
	 *
	 * @param id
	 */
	public void delete(Long id){
		taskRepository.deleteById(id);
	}

	public List<Task> findByAuthor(User author) {
		List<Task> tasks = taskRepository.findByAuthor(author);

		if (tasks.isEmpty())
			throw new TaskNotFoundException();

		return tasks;
	}
}

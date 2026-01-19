package com.openwebinars.todo_rest.repos;

import com.openwebinars.todo_rest.model.*;
import com.openwebinars.todo_rest.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository <Task, Long>{

    List<Task> findByAuthor(User author);
}

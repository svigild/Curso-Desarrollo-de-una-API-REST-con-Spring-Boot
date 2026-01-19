package com.openwebinars.todo_rest.model;

import com.openwebinars.todo_rest.users.User;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Task {

    @Id @GeneratedValue
    private Long Id;
    
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    
    private String title;
    
    @Lob
    private String description;
    
    private LocalDateTime deadLine;

	@ManyToOne // Un autor puede tener muchas tareas
	private User author;
    
    // Equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(Id, createdAt, deadLine, description, title);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(Id, other.Id) && Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(deadLine, other.deadLine) && Objects.equals(description, other.description)
				&& Objects.equals(title, other.title);
	}
    
   
}

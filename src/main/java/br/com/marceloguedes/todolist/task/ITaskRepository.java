package br.com.marceloguedes.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    //Método que lista  todas as task com base no ID do User
    List<TaskModel> findByIdUser(UUID idUser);
}

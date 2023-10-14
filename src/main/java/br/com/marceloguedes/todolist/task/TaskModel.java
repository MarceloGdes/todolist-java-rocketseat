package br.com.marceloguedes.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

 /* 
     * O que as tarefas devem ter
     * ID
     * autor (ID_úsuario)
     * Descrição
     * Titulo
     * Data de inicio
     * Data de término
     * Prioridade
     */

@Data
@Entity(name = "tb_task")
public class TaskModel {
   
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    //Limitando a quantidade de caracteres
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;


    //Validação de titulo
    //throws Exception joga o retorno do throw new Exception para a camada a cima que no caso é o ExceptionHandlerController
    public void setTitle(String title) throws Exception {
        if(title.length() > 50) {
            throw new Exception("O titulo deve ter até 50 caracteres!");
        }

        this.title = title;
    }

  
}

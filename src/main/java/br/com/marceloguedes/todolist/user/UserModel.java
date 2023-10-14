package br.com.marceloguedes.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import lombok.Data;

//Annotation Data do Lombok adiciona automaticamente os setters e getters 
@Data

// Criando a entidade da tabela no banco de dados combase nesse objeto de usuarios
@Entity(name="tb_users")
public class UserModel {
     //Modelo base para ser criado um usuario no metodo create do UserController
    
    @Id  //Definindo a primary key com a notation @Id
    @GeneratedValue(generator = "UUID") //Definindo o gerador do UUID
    private UUID id;


    @Column(unique = true)// definindo o usuario unico
    private String username;
    
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    //Para definirmos e acessarmos atributos privados no java usamos os médotos getters e setters
   /* Subistituimos pelo Lombok para dar eficiencia no codigo
    //passamos um username que vai ser adicionado ao atributo privado 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    } */
}

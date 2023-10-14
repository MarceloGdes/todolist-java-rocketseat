*API to-do list *

 - Utilizado o Spring Boot como framework de desenvolvimento e
   gerenciamento do back-end.

 - Spring initialiazer -> utilizado para para iniciar o pacote do   
   projeto https://start.spring.io.

 - Lombok -> usado para facilitar as criações de getters e setters e   
   outros métodos de uma classe
   
 - Spring Dev Tools -> ativar o live reload

 - Spring Data JPA -> utilizado para gerenciamento de arquivos e comunicação
   com o banco de dados
   
 - H2 Database - banco de dados em memória, caso reiniciado é perdido os
   dados do banco
   *Painel de gerencimento do banco só funciona no localhost*

		    http://localhost:8080/h2-console/
            login admin
            senha admin
            data_source jdbc:h2:mem:todolist

 - BCrypt para faz o gerenciamento criptográfico de senhas

 - Render.com -> Onde o projeto esta em operação e onde foi feito o deploy

 - DOCKER -> Utilizado para configurar o ambiente em que a aplicação vai rodar

**Utilizando a API**
https://todolist-rocket-85df.onrender.com
<br>

Criar um usuário

    POST https://todolist-rocket-85df.onrender.com/users/
        {
        	"name": "admin",
        	"username": "admin", //Username é único no banco de dados
        	"password": "12345"
        }
        
<br>
<br>
 Criar Uma tarefa
 
 Você precisa de uma autenticação básica (username e senha) com um usuário criado para poder criar uma tarefa.
	   

    POST https://todolist-rocket-85df.onrender.com/tasks/
    {
    	"description": "descrição da tarefa",
    	"title": "titulo da tarefa",
    	"priority": "ALTA",
    	"startAt": "2023-10-15T12:30:00", //data de inicio da tarefa
    	"endAt": "2023-10-20T15:35:00"  //data final da tarefa
    }

<br>
<br>
Listar tarefas

Necessário autenticação também

    GET https://todolist-rocket-85df.onrender.com/tasks/

<br>
<br>
Alterar uma tarefa

Necessário autenticação e informar o id da task como argumento no path. 
No body você coloca as chaves com valor que você quer alterar.

	PUT https://todolist-rocket-85df.onrender.com/tasks/id

	{
		"description": "Atualizando a descrição",
		"title": "Alterando o titulo"
	}

Projeto realizado com o curso de Java disponibilizado pela [Rocketseat!](https://www.rocketseat.com.br)

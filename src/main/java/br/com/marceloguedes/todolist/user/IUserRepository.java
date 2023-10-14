package br.com.marceloguedes.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//Interface é um modelo/contrato dentro da aplicação
//Temos definição de metodos mas não é feit a implementação do métodos
//<T, ID> é um generator que recebe o "T" que no caso é a classe/entidade que a interface ta representando e qual o Tipo de ID da entidade assim o spring data faz o gerenciamento disponibilizando os métods
public interface IUserRepository extends JpaRepository <UserModel, UUID>{
    //Definindo um metodo para o repositório, como ele é gerenciado pelo spring jpa ele automaticamente entende o que o método definido que fazer
    UserModel findByUsername(String username);
}

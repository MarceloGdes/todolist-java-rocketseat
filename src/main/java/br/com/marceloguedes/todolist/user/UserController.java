package br.com.marceloguedes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/* 
 * Modificadores -> definem o acesos a essas classes
 * 
 * public
 * private -> acesso a somente alguns atributos da classe
 * protected -> somente o proprio pacakge tem acesso
 */
@RestController
// definindo a rota do controler de usuarios para /users
@RequestMapping("/users")
public class UserController {

  @Autowired //annotation do spring que gerencia o ciclo de vida de um atributo
  // Utilizando a interface criada
  private IUserRepository userRepository;

  /*
   * Tipos
   * String
   * Integer -> num inteiros
   * Duble - > numeros decimais com bastante casas
   * Float -> números decimais com menos casas
   * char -> caracteres
   * Date
   * vois -> quando não queremos retornar nada no método
   */

  // Função para criar um úsuario
  // Como é uma cadastro de usuaario tem que ser o método POST com os dados no
  // corpo da requisição
  @PostMapping("/")
  // o UserModel é o tipo do argumento recebido no metodo
  //ReponseEntity da a possibilidade de ter mais de um retorno do método
  public ResponseEntity create(@RequestBody UserModel userModel) {
    //Validando se ja tem usuario criado na DB
    var user = this.userRepository.findByUsername(userModel.getUsername());

    if(user != null) {
      //Enviando status HTTP e mesnagem de erro
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado");
    }

    //Criptografando a senha do úsuario
    var hashedPassword = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());
    
    userModel.setPassword(hashedPassword);

    //Salvando o usuario no banco de dados 
    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userCreated);
  }
}

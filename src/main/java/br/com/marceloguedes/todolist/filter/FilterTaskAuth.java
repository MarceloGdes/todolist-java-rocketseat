package br.com.marceloguedes.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marceloguedes.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// Toda requisição vai passar pelofiltro primeiro antes de ir para os
// controllers
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                var servletPath = request.getServletPath();

                if (servletPath.startsWith("/tasks/")) {
                    // Pegar a autenticação (usuário e senha)
                    var authorization = request.getHeader("Authorization");

                    // System.out.println("Authorization");
                    // System.out.println(authorization);

                    // Removendo o "Basic" com o metodo substring e tirando os espaços com o trim
                    var authEncoded = authorization.substring("Basic".length()).trim();
                    // System.out.println(authEncoded);

                    // Decodificando o base 64
                    // Defiindo o array de bytes
                    byte[] authDecoded = Base64.getDecoder().decode(authEncoded);
                    // System.out.println(authDecoded);

                    // Tranformando em string
                    var authString = new String(authDecoded);
                    // System.out.println(authString);

                    String[] credentials = authString.split(":");
                    String username = credentials[0];
                    String password = credentials[1];

                    // Validar usuarios

                    var user = this.userRepository.findByUsername(username);

                    if (user == null) {
                        response.sendError(401, "Usuário não encontrado");
                    } else {
                        // Validação de senha
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                        if (passwordVerify.verified) {
                            // Segue viajem
                            request.setAttribute("idUser", user.getId());
                            filterChain.doFilter(request, response);

                        } else {
                            response.sendError(401);
                        }

                    }
                } else {
                    filterChain.doFilter(request, response);
                }

    }

}
package br.com.devpiologo.gestao_vagas.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("Usuário ou e-mail já estão em uso.");
    }
}

package br.com.devpiologo.gestao_vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;

    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo não deve conter caracteres especiais.")
    private String username;

    @Email(message = "Must be a valid e-mail.")
    private String email;

    @Length(min = 10, max = 50)
    private String password;
    private String description;
    private String[] skills;
}

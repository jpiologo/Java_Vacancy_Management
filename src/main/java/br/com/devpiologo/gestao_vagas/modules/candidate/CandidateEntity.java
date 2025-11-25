package br.com.devpiologo.gestao_vagas.modules.candidate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class CandidateEntity {
    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "Special characters are not allowed.")
    private String username;

    @Email(message = "Must be a valid e-mail.")
    private String email;

    @Length(min = 10, max = 50, message = "Password must have between 10 and 50 characters.")
    private String password;
    private String description;
    private String[] skills;
}

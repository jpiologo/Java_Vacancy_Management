package br.com.devpiologo.gestao_vagas.modules.candidate.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @CreationTimestamp
    private LocalDateTime createdAt;
}

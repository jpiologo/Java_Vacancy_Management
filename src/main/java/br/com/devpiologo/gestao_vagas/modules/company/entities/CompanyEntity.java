package br.com.devpiologo.gestao_vagas.modules.company.entities;

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

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @Email(message = "Must be a valid e-mail address.")
    private String email;
    private String website;
    private String description;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "Special characters are not allowed.")
    private String username;

    @Length(min = 10, max = 50, message = "Password must have between 10 and 50 characters.")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

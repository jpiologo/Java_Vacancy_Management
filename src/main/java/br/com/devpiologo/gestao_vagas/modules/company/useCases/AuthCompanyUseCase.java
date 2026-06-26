package br.com.devpiologo.gestao_vagas.modules.company.useCases;

import br.com.devpiologo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.devpiologo.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.devpiologo.gestao_vagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Invalid username or password");
                }
        );

        //Verifica ambas as senhas
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        //Se as senhas não baterem, retorna erro
        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        //Se as senhas baterem, gera o JWT
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiration = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("javagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(expiration)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .acess_token(token)
                .expiration(expiration)
                .build();

        return authCompanyResponseDTO;
    }
}

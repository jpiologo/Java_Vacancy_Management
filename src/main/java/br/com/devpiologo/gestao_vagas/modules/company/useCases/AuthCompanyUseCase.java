package br.com.devpiologo.gestao_vagas.modules.company.useCases;

import br.com.devpiologo.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.devpiologo.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import javax.naming.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Company not found");
                }
        );

        //Verifica ambas as senhas
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        //Se as senhas não baterem, retorna erro
        if(!passwordMatches) {
            throw new AuthenticationException();
        }
    }
}

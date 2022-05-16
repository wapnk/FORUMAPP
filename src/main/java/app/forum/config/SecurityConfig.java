package app.forum.config;

import app.forum.model.Uzytkownik;
import app.forum.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UzytkownikService uzytkownikService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uzytkownikService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        uzytkownikService.zapisz(Uzytkownik.builder()
                .haslo(passwordEncoder().encode("123"))
                .nazwa("user").build());

        http.csrf().disable()
                .httpBasic().disable();
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").authenticated()
                .and().formLogin();

    }
}

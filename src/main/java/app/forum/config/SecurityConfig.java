package app.forum.config;

import app.forum.model.Uzytkownik;
import app.forum.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProviderImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uzytkownikService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        uzytkownikService.zapisz(Uzytkownik.builder()
//                .haslo(passwordEncoder().encode("123"))
                .haslo("123")
                .nazwa("user1").build());
        uzytkownikService.zapisz(Uzytkownik.builder()
                .haslo("1234")
                .nazwa("user2").build());

        http.csrf().disable()
                .httpBasic().disable()
                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/sql/**").permitAll()
                .antMatchers("/glowna").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/konto/zarejestruj/**").permitAll()
                .and().formLogin().permitAll();

    }
}
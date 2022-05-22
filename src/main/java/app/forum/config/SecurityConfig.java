package app.forum.config;

import app.forum.service.UzytkownikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UzytkownikService uzytkownikService;

    @Autowired
    private AuthenticationProviderImpl authenticationProvider;

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

        http.csrf().disable()
                .httpBasic().disable()
                .headers().frameOptions().disable();
        http
                .authorizeRequests()
                .antMatchers("/sql/**").hasRole("ADMIN")//.permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")//.permitAll()
                .antMatchers("/glowna").permitAll()
                .antMatchers("/api/uzytkownik/zarejestruj").permitAll()
                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .and().formLogin().permitAll();

    }
}

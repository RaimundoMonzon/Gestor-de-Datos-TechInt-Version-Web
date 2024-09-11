package Programacion2.HoldingEmpresas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    // Permitir acceso a recursos estáticos
                    .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    // Permitir acceso a páginas de login, registro y logout
                    .requestMatchers("/login", "/register", "/logout").permitAll()
                    // Todas las demás rutas requieren autenticación
                    .anyRequest().authenticated()
            )
            .formLogin(form -> 
                form
                    .loginPage("/login")  // Página de login personalizada
                    .permitAll()           // Permitir acceso sin autenticación
                    .defaultSuccessUrl("/home", true)  // Redirigir a /home tras el login exitoso (opcional)
            )
            .logout(logout -> 
                logout.permitAll()  // Permitir logout sin autenticación
            )
            .csrf().disable();  // Deshabilitar CSRF (opcional para desarrollo)

        return http.build();
    }
}

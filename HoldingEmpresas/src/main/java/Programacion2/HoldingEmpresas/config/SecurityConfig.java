package Programacion2.HoldingEmpresas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests

                    // Permitir acceso a recursos estáticos
                    .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                    // Permitir acceso a páginas de login, registro y logout
                    .requestMatchers("/login", "/register", "/logout").permitAll()

                    // Todas las demás rutas requieren autenticación
                    .requestMatchers("/home").hasRole("ADMIN")
                    .requestMatchers("/busqueda").hasRole("ADMIN")
                    .requestMatchers("/create").hasRole("ADMIN")

                    .anyRequest().authenticated()
            )
            .formLogin(form -> 
                form
                    .loginPage("/login")  // Página de login personalizada
                    .defaultSuccessUrl("/home", true)  // Redirigir a /home tras el login exitoso (opcional)
            )
            .logout(logout -> 
                logout.permitAll()  // Permitir logout sin autenticación
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No se encontro el Usuario."));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}

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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests

                        // Permitir acceso a recursos estáticos
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // Permitir acceso a páginas de login, registro y logout
                        .requestMatchers("/login", "/register", "/logout", "/error").permitAll()

                        // Todas las demás rutas requieren autenticación
                        .requestMatchers("/profile").hasAnyRole("ADMIN", "ASESOR", "VENDEDOR")
                        .requestMatchers("/home").hasAnyRole("ADMIN", "ASESOR", "VENDEDOR")
                        .requestMatchers("/busqueda").hasAnyRole("ADMIN", "ASESOR", "VENDEDOR")
                        .requestMatchers("/create/user").hasAnyRole("ADMIN", "VENDEDOR")
                        .requestMatchers("/create/**").hasRole("ADMIN")
                        .requestMatchers("/edit/user").hasAnyRole("ADMIN", "VENDEDOR")
                        .requestMatchers("/edit/**").hasRole("ADMIN")
                        .requestMatchers("/register-sale").hasRole("VENDEDOR")
                        .requestMatchers("/sign-contract").hasRole("ASESOR")

                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // Página de login personalizada
                        .defaultSuccessUrl("/home", true) // Redirigir a /home tras el login exitoso (opcional)
                        .failureHandler(authenticationFailureHandler()))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/logout-success")
                        .permitAll());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("No se encontro el Usuario."));
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // Custom failure handler
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            // Redirect to the login page with error parameters
            response.sendRedirect("/login?error=true");
        };
    }

}

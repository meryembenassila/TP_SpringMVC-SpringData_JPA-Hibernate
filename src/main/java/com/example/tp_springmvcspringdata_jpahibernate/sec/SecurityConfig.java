package com.example.tp_springmvcspringdata_jpahibernate.sec;
//pour personnaliser sécurité de spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    //InMemoryUserDetailsManager : permet de créer des utilisateurs stockés en mémoire (pas en base de données).
    public InMemoryUserDetailsManager  inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder().encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build()
                // Ces rôles serviront pour gérer l’accès aux URL.
                );}
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
               // .formLogin(Customizer.withDefaults()) //Active le formulaire de login par défaut fourni par Spring Security.
                .formLogin(fl->fl.loginPage("/login").permitAll())
                .authorizeHttpRequests(ar ->ar.requestMatchers("/user/**").hasRole("USER"))//Toutes les URL commençant par /index/ sont accessibles uniquement aux utilisateurs avec le rôle USER.
                .authorizeHttpRequests(ar ->ar.requestMatchers("/admin/**").hasRole("ADMIN"))//Les URL /save/... et /delete/... sont réservées aux admins.
                .authorizeHttpRequests(ar ->ar.requestMatchers("/public/**","/webjars/**").permitAll())//acceder aux bootstrab meme si je suis pas authentifié
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated()) //Toutes les autres URL nécessitent que l’utilisateur soit connecté (peu importe le rôle).
                .exceptionHandling(eh->eh.accessDeniedPage("/notAuthorized"))
                .build();
    }
}

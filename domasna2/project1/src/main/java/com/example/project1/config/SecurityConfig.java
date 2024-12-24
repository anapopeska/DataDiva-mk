// package com.example.project1.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//         .authorizeHttpRequests(auth -> auth
//           .requestMatchers("/login", "/register", "/css/**", "/js/**", "/images/**","/api/users/login","/api/users/register").permitAll()
//           .anyRequest().authenticated()
//         )
//         .formLogin(form -> form
//         .loginPage("/login")
//         .loginProcessingUrl("/api/users/login") // Автоматски процесирање на логирање
//         .defaultSuccessUrl("/today", true) // Пренасочување на success
//         .failureUrl("/?error=true") // Пренасочување на failure
//         .permitAll()
//     )
//     .logout(logout -> logout
//         .logoutUrl("/logout")
//         .logoutSuccessUrl("/")
//         .permitAll());
//       return http.build();
//   }
//     @Bean
// public PasswordEncoder passwordEncoder() {
//     return new BCryptPasswordEncoder();
// }
// }

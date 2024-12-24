// package com.example.project1.service;

// import com.example.project1.model.User;
// import com.example.project1.repository.UserRepository;
// import lombok.RequiredArgsConstructor;

// import org.springframework.stereotype.Service;

// @Service
// @RequiredArgsConstructor
// public class CustomUserDetailsService implements UserDetailsService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         // побарај user во базата
//         User user = userRepository.findByUsername(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("No user with username = " + username));
//         System.out.println("Loaded user: " + user.getUsername()); // Debug
//         System.out.println("Password: " + user.getPassword()); // Debug
//         // врати userDetails
//         return org.springframework.security.core.userdetails.User
//                 .withUsername(user.getUsername())
//                 .password(user.getPassword()) // внимавај, треба да биде енкриптирана

//                 .build();
//     }
// }

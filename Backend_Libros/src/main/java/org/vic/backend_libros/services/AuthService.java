package org.vic.backend_libros.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.vic.backend_libros.models.AuthResponse;
import org.vic.backend_libros.models.LoginRequest;
import org.vic.backend_libros.models.RegisterRequest;
import org.vic.backend_libros.models.User;
import org.vic.backend_libros.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getCorreo(), request.getClave()));
        UserDetails user = userRepository.findByCorreo(request.getCorreo()).orElseThrow();

        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse register(RegisterRequest request) {

        User newUser = User.builder()
                .correo(request.getCorreo())
                .clave(passwordEncoder.encode(request.getClave()))
                .nombre_Usuario(request.getNombre_Usuario())
                .build();

        userRepository.save(newUser);

        new AuthResponse();
        return AuthResponse.builder()
                .token(jwtService.getToken(newUser))
                .build();

    }


}

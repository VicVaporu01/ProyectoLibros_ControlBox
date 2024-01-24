package org.vic.backend_libros.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.vic.backend_libros.models.AuthResponse;
import org.vic.backend_libros.models.BookAndOpinion;
import org.vic.backend_libros.models.LoginRequest;
import org.vic.backend_libros.models.RegisterRequest;
import org.vic.backend_libros.services.AuthService;
import org.vic.backend_libros.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse pruebaAuth= new AuthResponse();

        pruebaAuth = authService.login(request);

        System.out.println("Auth Response: "+pruebaAuth);

        return ResponseEntity.ok(pruebaAuth);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @GetMapping(value = "books/{id}")
    public ResponseEntity<BookAndOpinion> getBooksAndOpinions(@PathVariable Integer id) {
        BookAndOpinion bookAndOpinions = userService.getBookAndOpinions(id);

        if (bookAndOpinions == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(bookAndOpinions);

    }

}

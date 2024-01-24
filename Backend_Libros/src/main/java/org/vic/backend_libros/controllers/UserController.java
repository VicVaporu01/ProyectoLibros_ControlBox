package org.vic.backend_libros.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.vic.backend_libros.models.*;
import org.vic.backend_libros.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class UserController {
    private final UserService userService;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) { // Metodo para obtener un usuario por su ID
        UserDTO userDTO = userService.getUser(id); // Obtiene el usuario del servicio de usuario
        if (userDTO == null) { // Si el usuario no se encuentra, devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userDTO); // Si el usuario se encuentra, devuelve el usuario con un 200 OK
    }

    @GetMapping(value = "logged")
    public ResponseEntity<UserDTO> getLoggedUser() { // Metodo para obtener el usuario actualmente autenticado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // Obtiene el principal de la autenticacion actual

        if (principal instanceof UserDetails) { // Si el principal es una instancia de UserDetails
            String username = ((UserDetails) principal).getUsername(); // Obtiene el nombre de usuario del principal
            UserDTO userDTO = userService.getLoggedUser(username); // Obtiene el usuario del servicio de usuario usando el nombre de usuario
            if (userDTO == null) { // Si el usuario no se encuentra, devuelve un 404 Not Found
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(userDTO); // Si el usuario se encuentra, devuelve el usuario con un 200 OK
        }

        return ResponseEntity.badRequest().build(); // Si el principal no es una instancia de UserDetails, devuelve un 400 Bad Request

    }

    @GetMapping(value = "opinions/{id}")
    public ResponseEntity<List<UserAndOpinion>> getUserAndOpinions(@PathVariable Integer id) {
        List<UserAndOpinion> userAndOpinions = userService.getUserAndOpinions(id); // Obtiene las opiniones del usuario del servicio de usuario
        if (userAndOpinions == null) { // Si el usuario no se encuentra, devuelve un 404 Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userAndOpinions); // Si el usuario se encuentra, devuelve el usuario con un 200 OK
    }

    @PostMapping(value = "saveOpinion")
    public ResponseEntity<UserResponse> saveOpinion(@RequestBody Opinion opinion){
        return ResponseEntity.ok(userService.saveOpinion(opinion));
    }

//    @PutMapping(value = "{id}") // Define un endpoint PUT para actualizar un usuario
//    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest) { // Metodo para actualizar un usuario
//        return ResponseEntity.ok(userService.updateUser(userRequest)); // Actualiza el usuario usando el servicio de usuario y devuelve la respuesta con un 200 OK
//    }


}
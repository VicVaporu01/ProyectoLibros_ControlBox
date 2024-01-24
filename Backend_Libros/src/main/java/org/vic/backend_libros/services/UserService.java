package org.vic.backend_libros.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vic.backend_libros.models.*;
import org.vic.backend_libros.repositories.OpinionRepository;
import org.vic.backend_libros.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final OpinionRepository opinionRepository;

    public UserDTO getUser(Integer id) {
        User userById = userRepository.findById(id).orElse(null);

        if (userById != null) {
            return UserDTO.builder()
                    .usuario_Id(userById.getUsuario_Id())
                    .correo(userById.getCorreo())
                    .nombre_Usuario(userById.getNombre_Usuario())
                    .build();
        }

        return null;

    }

    public UserDTO getLoggedUser(String correo) {
        User userByCorreo = userRepository.findByCorreo(correo).orElse(null);

        if (userByCorreo != null) {
            return UserDTO.builder()
                    .usuario_Id(userByCorreo.getUsuario_Id())
                    .correo(userByCorreo.getCorreo())
                    .nombre_Usuario(userByCorreo.getNombre_Usuario())
                    .build();
        }

        return null;

    }

    public List<UserAndOpinion> getUserAndOpinions(Integer usuario_Id) {
        return userRepository.getUserAndOpinions(usuario_Id);
    }

    public Book getBooks(Integer libro_Id) {
        return userRepository.getBooks(libro_Id);
    }

    public BookAndOpinion getBookAndOpinions(Integer libro_Id) {
        Book book = getBooks(libro_Id);
        List<OpinionDTO> opinionDTOList = userRepository.getOpinionsByBookId(libro_Id);
        return BookAndOpinion.builder()
                .book(book)
                .opiniones(opinionDTOList)
                .build();
    }

    public UserResponse saveOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
        return new UserResponse("La opinión se registró satisfactoriamente");
    }

}

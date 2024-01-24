package org.vic.backend_libros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vic.backend_libros.models.Book;
import org.vic.backend_libros.models.OpinionDTO;
import org.vic.backend_libros.models.User;
import org.vic.backend_libros.models.UserAndOpinion;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByCorreo(String correo);

    @Query("SELECT new org.vic.backend_libros.models.UserAndOpinion(u.nombre_Usuario, u.correo, l.titulo, o.comentario, o.fecha_Opinion) FROM User u JOIN Opinion o ON u.usuario_Id=o.usuario_Id JOIN Book l ON o.libro_Id=l.libro_Id WHERE u.usuario_Id=:usuario_Id")
    List<UserAndOpinion> getUserAndOpinions(Integer usuario_Id);

    @Query("SELECT new org.vic.backend_libros.models.Book(l.libro_Id, l.titulo, l.autor, l.categoria, l.resumen) FROM Book l WHERE l.libro_Id=:libro_Id")
    Book getBooks(Integer libro_Id);

    @Query("SELECT new org.vic.backend_libros.models.OpinionDTO(u.nombre_Usuario, u.correo, o.comentario, o.calificacion, o.fecha_Opinion) FROM User u JOIN Opinion o ON u.usuario_Id=o.usuario_Id WHERE o.libro_Id=:libro_Id")
    List<OpinionDTO> getOpinionsByBookId(Integer libro_Id);

}

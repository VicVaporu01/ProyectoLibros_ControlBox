package org.vic.backend_libros.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Libros")
public class Book {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libro_Id", unique = true, nullable = false)
    private int libro_Id;

    private String titulo;
    private String autor;
    private String categoria;
    private String resumen;
}

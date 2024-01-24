package org.vic.backend_libros.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Opiniones")
public class Opinion {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_Id", unique = true, nullable = false)
    private int opinion_Id;

    private int usuario_Id;
    private int libro_Id;
    private int calificacion;
    private String comentario;
    private Date fecha_Opinion;
}

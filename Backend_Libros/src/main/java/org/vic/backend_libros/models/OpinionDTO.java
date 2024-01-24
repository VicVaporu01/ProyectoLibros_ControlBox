package org.vic.backend_libros.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDTO {
    private String nombre_Usuario;
    private String correo;
    private String comentario;
    private int calificacion;
    private Date fecha_Opinion;
}

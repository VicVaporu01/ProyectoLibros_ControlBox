package org.vic.backend_libros.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private int usuario_Id;
    private String correo;
    private String clave;
    private String nombre_Usuario;
}

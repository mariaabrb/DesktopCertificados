package org.example.certifyproadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDto {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
}

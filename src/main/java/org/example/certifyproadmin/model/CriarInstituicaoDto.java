package org.example.certifyproadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriarInstituicaoDto {
    private String nomeInstituicao;

    private String nomeAdmin;
    private String emailAdmin;
    private String senhaAdmin;
    private String cpfAdmin;
}
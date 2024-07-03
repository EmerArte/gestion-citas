package com.codex.gestioncitasclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CrearCitaDto {
    private LocalDate fecha;
    private String hora;
    private String estado;
    private Integer tipoDocumentoUsuario;
    private String numDocumentoUsuario;
    private Integer tipoDocumentoIdDoctor;
    private String numDocumentoDoctor;
}

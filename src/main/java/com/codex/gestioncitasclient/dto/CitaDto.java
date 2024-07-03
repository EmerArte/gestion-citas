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
public class CitaDto {
    private Integer id;
    private LocalDate fecha;
    private String hora;
    private String estado;
    private Integer tipoDocumentoIdDoctor;
    private String numDocumentoDoctor;
    private Integer tipoDocumentoIdUsuario;
    private String numDocumentoUsuario;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}

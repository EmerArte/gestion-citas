package com.codex.gestioncitasclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorDto {
    private Integer tipoDocumentoId;
    private String numeroDocumento;
    private Integer idConsultorio;
    private String nombreCompleto;
    private String especialidad;
}

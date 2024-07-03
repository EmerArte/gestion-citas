package com.codex.gestioncitasclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "doctor", schema="gestion_citas")
@NoArgsConstructor
@Data
public class Doctor {
    private Integer tipoDocumentoId;
    private String numeroDocumento;
    private Integer idConsultorio;
    private String nombreCompleto;
    private String especialidad;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}

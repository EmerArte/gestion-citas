package com.codex.gestioncitasclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "cita", schema="gestion_citas")
@NoArgsConstructor
@Data
public class Cita {
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

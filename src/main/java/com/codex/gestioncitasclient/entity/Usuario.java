package com.codex.gestioncitasclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "usuario", schema="gestion_citas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Usuario {
    private String nombreCompleto;
    private Integer tipoDocumentoId;
    private String numeroDocumento;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}

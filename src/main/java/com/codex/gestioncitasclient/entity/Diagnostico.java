package com.codex.gestioncitasclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "diagnostico", schema="gestion_citas")
@NoArgsConstructor
@Data
public class Diagnostico {
    private Integer id;
    private String descripcion;
    private String formulaMedica;
    private Integer idCita;
    private String fechaCreacion;
    private String fechaModificacion;
}

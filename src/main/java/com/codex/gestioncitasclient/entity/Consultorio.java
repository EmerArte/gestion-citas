package com.codex.gestioncitasclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "consultorio", schema="gestion_citas")
@NoArgsConstructor
@Data
public class Consultorio {
    private Integer id;
    private String nombre;
    private String ubicacion;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;
}

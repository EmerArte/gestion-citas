package com.codex.gestioncitasclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tipo_documento", schema="gestion_citas")
@NoArgsConstructor
@Data
public class TipoDocumento {
    private Integer id;
    private String abreviacion;
    private String descripcion;
}

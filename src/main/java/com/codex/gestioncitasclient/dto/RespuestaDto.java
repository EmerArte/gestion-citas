package com.codex.gestioncitasclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaDto {
    private String mensaje;
    private String codigo;
    private Object data;
}

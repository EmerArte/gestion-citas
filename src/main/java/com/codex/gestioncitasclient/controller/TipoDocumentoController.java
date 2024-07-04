package com.codex.gestioncitasclient.controller;

import com.codex.gestioncitasclient.dto.RespuestaDto;
import com.codex.gestioncitasclient.entity.TipoDocumento;
import com.codex.gestioncitasclient.repository.ITipoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class TipoDocumentoController {
    private final ITipoDocumentoRepository repository;
    @GetMapping("/tipodocumento/obtener")
    public ResponseEntity<RespuestaDto> obtenerTodos() {
        List<TipoDocumento> data = repository.listar();
        return ResponseEntity.ok(new RespuestaDto("success", "200", data));
    }
}

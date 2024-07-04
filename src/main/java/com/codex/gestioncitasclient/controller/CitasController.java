package com.codex.gestioncitasclient.controller;

import com.codex.gestioncitasclient.dto.CitaDto;
import com.codex.gestioncitasclient.dto.CrearCitaDto;
import com.codex.gestioncitasclient.dto.RespuestaDto;
import com.codex.gestioncitasclient.services.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class CitasController {
    private final CitaService citasService;

    @PostMapping("/citas/crear")
    public ResponseEntity<RespuestaDto> crearCita(@RequestBody CrearCitaDto crearCitaDto) {
        RespuestaDto respuestaDto = citasService.crearCita(crearCitaDto);
        return ResponseEntity.ok(respuestaDto);
    }
    @PutMapping("/citas/actualizar")
    public ResponseEntity<RespuestaDto> actualizarCita(@RequestBody CitaDto citaDto) {
        RespuestaDto respuestaDto = citasService.actualizarCita(citaDto);
        return ResponseEntity.ok(respuestaDto);
    }
    @GetMapping("/citas/obtener")
    public ResponseEntity<RespuestaDto> obtenerCitas() {
        RespuestaDto respuestaDto = citasService.obtenerCitas();
        return ResponseEntity.ok(respuestaDto);
    }

    @GetMapping("/citas/obtener/doctor/{numDocumento}")
    public ResponseEntity<RespuestaDto> obtenerCitasPorDoctor(@PathVariable String numDocumento) {
        RespuestaDto respuestaDto = citasService.obtenerCitasPorDoctor(numDocumento);
        return ResponseEntity.ok(respuestaDto);
    }

    @GetMapping("/citas/obtener/usuario/{tipoDocumentoId}/{numDocumento}")
    public ResponseEntity<RespuestaDto> obtenerCitasPorUsuario(@PathVariable String numDocumento, @PathVariable Integer tipoDocumentoId) {
        RespuestaDto respuestaDto = citasService.obtenerCitasPorUsuario(numDocumento, tipoDocumentoId);
        return ResponseEntity.ok(respuestaDto);
    }
}

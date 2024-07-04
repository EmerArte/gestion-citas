package com.codex.gestioncitasclient.controller;

import com.codex.gestioncitasclient.dto.DoctorDto;
import com.codex.gestioncitasclient.dto.RespuestaDto;
import com.codex.gestioncitasclient.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@CrossOrigin("*")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/doctor/crear")
    public ResponseEntity<RespuestaDto> crearDoctor(@RequestBody DoctorDto doctorDto) {
        RespuestaDto respuestaDto = doctorService.crearDoctor(doctorDto);
        return ResponseEntity.ok(respuestaDto);
    }

    @GetMapping("/doctor/obtener")
    public ResponseEntity<RespuestaDto> obtenerTodos() {
        RespuestaDto respuestaDto = doctorService.buscarTodosDoctores();
        return ResponseEntity.ok(respuestaDto);
    }
}

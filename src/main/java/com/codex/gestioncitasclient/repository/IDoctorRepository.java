package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Doctor;

import java.util.List;

public interface IDoctorRepository {

    Doctor crearDoctor(Doctor doctor);
    List<Doctor> obtenerTodos();
    Doctor obtenerDoctorPorNumeroDocumento(String numeroDocumento, Integer tipoDocumentoId);
}

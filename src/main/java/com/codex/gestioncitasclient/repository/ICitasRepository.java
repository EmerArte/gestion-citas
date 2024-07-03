package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Cita;

import java.util.List;

public interface ICitasRepository {
    Cita crearCita(Cita cita);
    Cita actualizarCita(Cita cita);
    List<Cita> obtenerCitas();
    List<Cita> obtenerCitasPorDoctor(String numDocumento);
    List<Cita> obtenerCitasPorUsuario(String numDocumento, Integer tipoDocumentoId);
}

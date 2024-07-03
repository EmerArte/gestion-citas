package com.codex.gestioncitasclient.services;

import com.codex.gestioncitasclient.dto.DoctorDto;
import com.codex.gestioncitasclient.dto.RespuestaDto;
import com.codex.gestioncitasclient.entity.Doctor;
import com.codex.gestioncitasclient.repository.IDoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final IDoctorRepository doctorRepository;


    public RespuestaDto buscarTodosDoctores() {
        try {
            List<Doctor> doctores = doctorRepository.obtenerTodos();
            if(doctores.isEmpty()) {
                return new RespuestaDto("No se encontraron doctores", "404", null);
            }
            return new RespuestaDto("Doctores encontrados", "200", doctores);
        }catch (Exception e) {
            return new RespuestaDto("Error al buscar los doctores", "500", null);
        }

    }

    public RespuestaDto buscarDoctorPorDocumento(String numDocumento, Integer tipoDocumento) {
        try{
            Doctor doctor = doctorRepository.obtenerDoctorPorNumeroDocumento(numDocumento, tipoDocumento);
            if(doctor == null) {
                return new RespuestaDto("No se encontró el doctor", "404", null);
            }
            return new RespuestaDto("Doctor encontrado", "200", doctor);
        }catch (Exception e) {
            return new RespuestaDto("Error al buscar el doctor", "500", null);
        }

    }

    public RespuestaDto crearDoctor(DoctorDto doctor) {
        try{
            if(doctor == null) {
                return new RespuestaDto("No se pudo crear el doctor", "400", null);
            }
            Doctor doctorExistente = doctorRepository.obtenerDoctorPorNumeroDocumento(doctor.getNumeroDocumento(), doctor.getTipoDocumentoId());
            if(doctorExistente != null) {
                return new RespuestaDto("Ya existe un doctor con ese número de documento", "400", null);
            }
            Doctor doctorACrear = new Doctor();
            doctorACrear.setTipoDocumentoId(doctor.getTipoDocumentoId());
            doctorACrear.setNumeroDocumento(doctor.getNumeroDocumento());
            doctorACrear.setIdConsultorio(doctor.getIdConsultorio());
            doctorACrear.setNombreCompleto(doctor.getNombreCompleto());
            doctorACrear.setEspecialidad(doctor.getEspecialidad());
            doctorACrear.setFechaCreacion(LocalDate.now());
            doctorACrear.setFechaModificacion(LocalDate.now());
            Doctor doctorCreado = doctorRepository.crearDoctor(doctorACrear);
            if(doctorCreado == null) {
                return new RespuestaDto("No se pudo crear el doctor", "400", null);
            }
            return new RespuestaDto("Doctor creado", "200", doctorCreado);
        }catch (Exception e) {
            e.printStackTrace();
            return new RespuestaDto("Error al crear el doctor", "500", null);
        }

    }
}

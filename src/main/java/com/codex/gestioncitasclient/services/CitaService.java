package com.codex.gestioncitasclient.services;

import com.codex.gestioncitasclient.dto.CitaDto;
import com.codex.gestioncitasclient.dto.CrearCitaDto;
import com.codex.gestioncitasclient.dto.RespuestaDto;
import com.codex.gestioncitasclient.entity.Cita;
import com.codex.gestioncitasclient.entity.Doctor;
import com.codex.gestioncitasclient.entity.Usuario;
import com.codex.gestioncitasclient.exeptions.DefaultException;
import com.codex.gestioncitasclient.repository.ICitasRepository;
import com.codex.gestioncitasclient.repository.IDoctorRepository;
import com.codex.gestioncitasclient.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {
    private final ICitasRepository citasRepository;
    private final IDoctorRepository doctorRepository;
    private final IUsuarioRepository usuarioRepository;
    public RespuestaDto crearCita(CrearCitaDto nuevaCita) {
        try{
            // Validar si el doctor existe
            Doctor doctor = doctorRepository.obtenerDoctorPorNumeroDocumento(nuevaCita.getNumDocumentoDoctor(), nuevaCita.getTipoDocumentoIdDoctor());
            if(doctor== null ) {
                throw new DefaultException("El Doctor no existe", 401);
            }
            List<Cita> obtenerCitasDoctor = citasRepository.obtenerCitasPorDoctor(nuevaCita.getNumDocumentoDoctor());
            // Se valida que no exista una cita para el doctor en la fecha y hora seleccionada
            obtenerCitasDoctor.stream()
                    .filter(cita -> cita.getFecha().equals(nuevaCita.getFecha()) && cita.getHora().equals(nuevaCita.getHora()))
                    .findFirst()
                    .ifPresent(cita -> {
                        throw new DefaultException("Ya existe una cita para el doctor en la fecha y hora seleccionada", 400);
                    });
            // Si no existe una cita para el doctor en la fecha y hora seleccionada se crea la cita
                // Registrar el usuario en la base de datos si no existe
            Usuario usuario = usuarioRepository.obtenerUsuarioPorDocumento_Tipo(nuevaCita.getNumDocumentoUsuario(),
                    nuevaCita.getTipoDocumentoUsuario());
            if(usuario == null){
                Usuario registroInicial = new Usuario();
                registroInicial.setTipoDocumentoId(nuevaCita.getTipoDocumentoUsuario());
                registroInicial.setNumeroDocumento(nuevaCita.getNumDocumentoUsuario());
                registroInicial.setFechaCreacion(LocalDate.now());
                registroInicial.setFechaCreacion(LocalDate.now());
                usuarioRepository.crearUsuario(registroInicial);
            }
            Cita cita = new Cita();
            cita.setFecha(nuevaCita.getFecha());
            cita.setHora(nuevaCita.getHora());
            cita.setEstado(nuevaCita.getEstado());
            cita.setTipoDocumentoIdDoctor(nuevaCita.getTipoDocumentoIdDoctor());
            cita.setNumDocumentoDoctor(nuevaCita.getNumDocumentoDoctor());
            cita.setTipoDocumentoIdUsuario(nuevaCita.getTipoDocumentoUsuario());
            cita.setNumDocumentoUsuario(nuevaCita.getNumDocumentoUsuario());
            cita.setFechaCreacion(LocalDate.now());
            cita.setFechaModificacion(LocalDate.now());
            citasRepository.crearCita(cita);
            return new RespuestaDto("Cita creada correctamente", "200", cita);


        }catch (DefaultException e){
            return new RespuestaDto(e.getMessage(), String.valueOf(e.getCode()), null);
        } catch (Exception e){
            e.printStackTrace();
            return new RespuestaDto("Error al crear la cita", "500", null);
        }
    }

    public RespuestaDto obtenerCitas() {
        try {
            List<Cita> citas = citasRepository.obtenerCitas();
            if(citas.isEmpty()) {
                return new RespuestaDto("No se encontraron citas", "404", null);
            }
            return new RespuestaDto("Citas encontradas", "200", citas);
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaDto("Error al obtener las citas", "500", null);
        }
    }

    public RespuestaDto obtenerCitasPorDoctor(String numDocumentoDoctor) {
        try{
            List<Cita> citas = citasRepository.obtenerCitasPorDoctor(numDocumentoDoctor);
            if(citas.isEmpty()) {
                return new RespuestaDto("No se encontraron citas", "404", null);
            }
            return new RespuestaDto("Citas encontradas", "200", citas);
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaDto("Error al obtener las citas", "500", null);
        }
    }
    public RespuestaDto actualizarCita(CitaDto cita){
        try{
            Cita citaActualizada = new Cita();
            citaActualizada.setId(cita.getId());
            citaActualizada.setFecha(cita.getFecha());
            citaActualizada.setHora(cita.getHora());
            citaActualizada.setEstado(cita.getEstado());
            citaActualizada.setTipoDocumentoIdDoctor(cita.getTipoDocumentoIdDoctor());
            citaActualizada.setNumDocumentoDoctor(cita.getNumDocumentoDoctor());
            citaActualizada.setTipoDocumentoIdUsuario(cita.getTipoDocumentoIdUsuario());
            citaActualizada.setNumDocumentoUsuario(cita.getNumDocumentoUsuario());
            citaActualizada.setFechaCreacion(cita.getFechaCreacion());
            citaActualizada.setFechaModificacion(LocalDate.now());
            citasRepository.actualizarCita(citaActualizada);
            return new RespuestaDto("Cita actualizada correctamente", "200", citaActualizada);
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaDto("Error al actualizar la cita", "500", null);
        }
    }
    public RespuestaDto obtenerCitasPorUsuario(String numDocumentoUsuario, Integer tipoDocumentoUsuario) {
        try{
            List<Cita> citas = citasRepository.obtenerCitasPorUsuario(numDocumentoUsuario, tipoDocumentoUsuario);
            if(citas.isEmpty()) {
                return new RespuestaDto("No se encontraron citas", "404", null);
            }
            return new RespuestaDto("Citas encontradas", "200", citas);
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaDto("Error al obtener las citas", "500", null);
        }

    }
}

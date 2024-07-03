package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepositoryImpl implements IDoctorRepository {
    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Doctor> doctorRowMapper = (rs, rowNum) -> {
        Doctor doctor = new Doctor();
        doctor.setNumeroDocumento(rs.getString("numDocumento"));
        doctor.setTipoDocumentoId(rs.getInt("tipoDocumentoId"));
        doctor.setIdConsultorio(rs.getInt("idConsultorio"));
        doctor.setNombreCompleto(rs.getString("nombreCompleto"));
        doctor.setEspecialidad(rs.getString("especialidad"));
        doctor.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
        doctor.setFechaModificacion(rs.getDate("fechaModificacion").toLocalDate());
        return doctor;
    };

    @Override
    public Doctor crearDoctor(Doctor doctor) {
        String sql = "INSERT INTO gestion_citas.doctor (idConsultorio, especialidad, tipoDocumentoId, numDocumento, nombreCompleto, fechaCreacion, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, doctor.getIdConsultorio(), doctor.getEspecialidad(), doctor.getTipoDocumentoId(), doctor.getNumeroDocumento(), doctor.getNombreCompleto(), doctor.getFechaCreacion(), doctor.getFechaModificacion() );
        return doctor;
    }

    @Override
    public List<Doctor> obtenerTodos() {
        String sql = "SELECT * FROM gestion_citas.doctor";
        return jdbcTemplate.query(sql, doctorRowMapper);
    }

    @Override
    public Doctor obtenerDoctorPorNumeroDocumento(String numDocumento, Integer tipoDocumentoId) {
        String sql = "SELECT * FROM gestion_citas.doctor WHERE numDocumento = ? AND tipoDocumentoId = ?";
        List<Doctor> doctores = jdbcTemplate.query(sql, new Object[]{numDocumento, tipoDocumentoId}, doctorRowMapper);
        if(doctores.isEmpty()) {
            return null;
        }
        return doctores.get(0);
    }

}

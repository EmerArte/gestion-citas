package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Cita;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CitasRepositoryImpl implements ICitasRepository {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Cita> citaRowMapper = (rs, rowNum) -> {
        Cita cita = new Cita();
        cita.setId(rs.getInt("id"));
        cita.setFecha(rs.getDate("fecha").toLocalDate());
        cita.setHora(rs.getString("hora"));
        cita.setEstado(rs.getString("estado"));
        cita.setTipoDocumentoIdDoctor(rs.getInt("tipoDocumentoIdDoctor"));
        cita.setNumDocumentoDoctor(rs.getString("numDocumentoDoctor"));
        cita.setTipoDocumentoIdUsuario(rs.getInt("tipoDocumentoIdUsuario"));
        cita.setNumDocumentoUsuario(rs.getString("numDocumentoUsuario"));
        cita.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
        cita.setFechaModificacion(rs.getDate("fechaModificacion").toLocalDate());
        return cita;
    };
    @Override
    public Cita crearCita(Cita cita) {
        String sql = "INSERT INTO gestion_citas.cita (fecha, hora, estado, tipoDocumentoIdDoctor," +
                " numDocumentoDoctor, tipoDocumentoIdUsuario, numDocumentoUsuario, " +
                "fechaCreacion, fechaModificacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cita.getFecha(), cita.getHora(), cita.getEstado(), cita.getTipoDocumentoIdDoctor(), cita.getNumDocumentoDoctor(), cita.getTipoDocumentoIdUsuario(), cita.getNumDocumentoUsuario(), cita.getFechaCreacion(), cita.getFechaModificacion());
        return cita;
    }

    @Override
    public Cita actualizarCita(Cita cita) {
        String sql = "UPDATE gestion_citas.cita SET fecha = ?, hora = ?, estado = ?, " +
                "tipoDocumentoIdDoctor = ?, numDocumentoDoctor = ?, tipoDocumentoIdUsuario = ?," +
                " numDocumentoUsuario = ?, fechaCreacion = ?, fechaModificacion = ? WHERE id = ?";
        jdbcTemplate.update(sql, cita.getFecha(), cita.getHora(), cita.getEstado(), cita.getTipoDocumentoIdDoctor(), cita.getNumDocumentoDoctor(), cita.getTipoDocumentoIdUsuario(), cita.getNumDocumentoUsuario(), cita.getFechaCreacion(), cita.getFechaModificacion(), cita.getId());
        return cita;
    }

    @Override
    public List<Cita> obtenerCitas() {
        String sql = "SELECT * FROM gestion_citas.cita";
        return jdbcTemplate.query(sql, citaRowMapper);
    }

    @Override
    public List<Cita> obtenerCitasPorDoctor(String numDocumento) {
        String sql = "SELECT * FROM gestion_citas.cita WHERE numDocumentoDoctor = ?";
        return jdbcTemplate.query(sql, new Object[]{numDocumento}, citaRowMapper);
    }

    @Override
    public List<Cita> obtenerCitasPorUsuario(String numDocumento, Integer tipoDocumento) {
        String sql = "SELECT * FROM gestion_citas.cita WHERE numDocumentoUsuario = ? AND tipoDocumentoIdUsuario = ?";
        return jdbcTemplate.query(sql, new Object[]{numDocumento, tipoDocumento}, citaRowMapper);
    }
}

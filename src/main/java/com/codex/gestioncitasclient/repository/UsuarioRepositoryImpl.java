package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements IUsuarioRepository{
    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Usuario> usuarioRowMapper = (rs, rowNum) -> {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(rs.getString("nombreCompleto"));
        usuario.setTipoDocumentoId(rs.getInt("tipoDocumentoId"));
        usuario.setNumeroDocumento(rs.getString("numDocumento"));
        usuario.setFechaCreacion(rs.getDate("fechaCreacion").toLocalDate());
        usuario.setFechaModificacion(rs.getDate("fechaModificacion").toLocalDate());
        return usuario;
    };
    @Override
    public Usuario crearUsuario(Usuario usuario) {
        String sql = "INSERT INTO gestion_citas.usuario (nombreCompleto, tipoDocumentoId, numDocumento, fechaCreacion, fechaModificacion) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, usuario.getNombreCompleto(), usuario.getTipoDocumentoId(), usuario.getNumeroDocumento(), usuario.getFechaCreacion(), usuario.getFechaModificacion());
        return usuario;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE gestion_citas.usuario SET nombreCompleto = ?, tipoDocumentoId = ?, numDocumento = ?, " +
                "fechaModificacion = ? WHERE tipoDocumentoId = ? AND numDocumento = ?";
        int update = jdbcTemplate.update(sql, usuario.getNombreCompleto(), usuario.getTipoDocumentoId(), usuario.getNumeroDocumento(),
                usuario.getFechaModificacion(), usuario.getTipoDocumentoId(), usuario.getNumeroDocumento());
        return update == 1 ? usuario : null;
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return jdbcTemplate.query("SELECT * FROM gestion_citas.usuario",usuarioRowMapper);
    }

    @Override
    public Usuario obtenerUsuarioPorDocumento_Tipo(String numDocumento, Integer tipoDocumento) {
        List<Usuario> usuarios = jdbcTemplate.query("SELECT * FROM gestion_citas.usuario WHERE numDocumento = ? AND tipoDocumentoId = ?",
                new Object[]{numDocumento, tipoDocumento}, usuarioRowMapper);
        if(usuarios.isEmpty()){
            return null;
        }
        return usuarios.get(0);
    }
}

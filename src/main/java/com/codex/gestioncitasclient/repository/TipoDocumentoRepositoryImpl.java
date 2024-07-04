package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.TipoDocumento;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class TipoDocumentoRepositoryImpl implements ITipoDocumentoRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<TipoDocumento> listar() {
        String sql = "SELECT * FROM gestion_citas.tipo_documento";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setId(rs.getInt("id"));
            tipoDocumento.setAbreviacion(rs.getString("abreviacion"));
            tipoDocumento.setDescripcion(rs.getString("descripcion"));
            return tipoDocumento;
        });
    }
}

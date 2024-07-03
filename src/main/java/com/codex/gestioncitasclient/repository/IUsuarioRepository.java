package com.codex.gestioncitasclient.repository;

import com.codex.gestioncitasclient.entity.Usuario;

import java.util.List;

public interface IUsuarioRepository {
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(Usuario usuario);
    List<Usuario> obtenerUsuarios();
    Usuario obtenerUsuarioPorDocumento_Tipo(String numDocumento, Integer tipoDocumento);
}

package org.luisdiaz.springcloud.micro.usuarios.services;

import org.luisdiaz.springcloud.micro.usuarios.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
}

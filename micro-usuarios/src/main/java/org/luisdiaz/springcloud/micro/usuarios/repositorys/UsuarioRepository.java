package org.luisdiaz.springcloud.micro.usuarios.repositorys;

import org.luisdiaz.springcloud.micro.usuarios.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Long> { // DAO --> data access object


}

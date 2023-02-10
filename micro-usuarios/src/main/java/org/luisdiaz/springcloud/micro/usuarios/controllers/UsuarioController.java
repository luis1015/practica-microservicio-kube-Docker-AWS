package org.luisdiaz.springcloud.micro.usuarios.controllers;

import org.luisdiaz.springcloud.micro.usuarios.models.Usuario;
import org.luisdiaz.springcloud.micro.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> listarUsuarios(){
        return usuarioService.listar();
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> usuarioPorId(@PathVariable(value = "id") Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok().body(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable(value = "id") Long id,@RequestBody Usuario usuario){
        Optional<Usuario> u = usuarioService.findById(id);
        if (u.isPresent()){
            Usuario usuario1 = u.get();
            usuario1.setNombre(usuario.getNombre());
            usuario1.setEmail(usuario.getEmail());
            usuario1.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario1));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") Long id){
        Optional<Usuario> u = usuarioService.findById(id);
        if (u.isPresent()){
            usuarioService.deleteById(id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();

    }

}

package com.sistema.examenes.controladores;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.examenes.entidades.Rol;
import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
		Set<UsuarioRol> roles = new HashSet<>();
		Rol rol =  new Rol();
		rol.setRolid(2L);
		rol.setRolnombre("ALUMNO");
		
		UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setUsuario(usuario);
		usuarioRol.setRol(rol);
		
		return usuarioService.guardarUsuario(usuario, roles);
		
		
	}
	
	@GetMapping("/{username}")
	public Usuario obtenerUsuario(@PathVariable String username) {
		return usuarioService.obtenerUsuario(username);
	}
	
	@DeleteMapping("/{id}")
	public void eliminarUsuario(@PathVariable Long id) {
		 usuarioService.eliminarUsuario(id);
	}
	
	
}

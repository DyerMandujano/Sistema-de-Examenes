package com.sistema.examenes.servicios.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal !=null) {
			System.out.println("EL USUARIO YA EXISTE");
			throw new Exception("El usuario ya esta presente");
		}else {
			//Recorremos todos los roles pasados por el parametro 'usuarioRoles'
			for(UsuarioRol usuarioRol: usuarioRoles) {
				//Aqui guardamos todos los roles en la BD
				rolRepository.save(usuarioRol.getRol());
			}
			//Obtenemos todos los roles Y añadimos todos los roles insertados en la collecion 'usuarioRoles'
			//al parametro 'usuario'
			usuario.getUsuarioRoles().addAll(usuarioRoles);
			//A la variable 'usuarioLocal' le asignamos todos los datos del 'usuario' que se esta registrando
			usuarioLocal = usuarioRepository.save(usuario);
		}
		//RETORNAMOS LA VARIABLE 'usuarioLocal'
		return usuarioLocal;
	}

	@Override
	public Usuario obtenerUsuario(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public void eliminarUsuario(Long usuarioId) {
		usuarioRepository.deleteById(usuarioId);
		
	}
}

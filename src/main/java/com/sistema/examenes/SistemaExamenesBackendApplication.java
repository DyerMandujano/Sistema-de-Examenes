package com.sistema.examenes;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.examenes.entidades.Rol;
import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioService;

//IMPLEMENTAMOS EL 'CommandLineRunner' PARA QUE PODAMOS IMPRIMIR EN CONSOLA
@SpringBootApplication
public class SistemaExamenesBackendApplication implements CommandLineRunner {

	//INYECTAMOS A LA INTERFAZ 'UsuarioService' YA QUE SPRING SOBREENTIENDE
	//Y VA A LLAMAR A LA IMPLEMENTACION DE ESTÉ SERVICIO
	@Autowired
	private UsuarioService usuarioService;
	
	public static void main(String[] args) {
		SpringApplication.run(SistemaExamenesBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//CREAMOS UNA INSTANCIA PARA CREAR UN NUEVO USUARIO
		Usuario usuario = new Usuario();
		//SETEAMOS TODOS LOS DATOS PARA ESTE USUARIO
		usuario.setNombre("Dyer");
		usuario.setApellido("mandujano");
		usuario.setUsername("dyermr");
		usuario.setPassword("12345");
		usuario.setEmail("dyer@gmail.com");
		usuario.setTelefono("987654321");
		usuario.setPerfil("foto.png");
		
		//CREAMOS UNA INSTANCIA PARA CREAR UN NUEVO ROL
		Rol rol = new Rol();
		//SETEAMOS TODOS LOS DATOS PARA ESTE ROL
		rol.setRolid(1L);
		rol.setRolnombre("ADMIN");
		
		//Creamos una coleccion 'Set' DE TIPO 'UsuarioRol' PARA LLENAR LA INFORMACION DE LA TABLA COMBINADA
		//ES DECIR, que se utilizará para almacenar los roles relacionados con un usuario en la clase "Usuario".
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		
		//CREAMOS UNA INSTANCIA PARA CREAR UN NUEVO UsuarioRol
		UsuarioRol usuarioRol = new UsuarioRol();
		//SETEAMOS EL ROL QUE YA DEFINIMOS 
		usuarioRol.setRol(rol);
		//SETEAMOS EL USUARIO QUE YA DEFINIMOS
		usuarioRol.setUsuario(usuario);
		//ACA AÑADIMOS A LA COLECCION 'usuarioRoles' TODOS LOS VALORES QUE SE ENCUENTRAN EN ALMACENADOS EN 'usuarioRol' 
		usuarioRoles.add(usuarioRol);
		//CREAMOS UNA VARIABLE 'usuarioGuardado' DE TIPO Usuario EN EL CUAL SE LE ESTÁ ASIGNANDO COMO VALORES EL objeto 'usuario' Y LA COLECCION 'usuarioRoles' 
		Usuario usuarioGuardado = usuarioService.guardarUsuario(usuario, usuarioRoles);
		System.out.println(usuarioGuardado.getUsername());
		
	}

}

package com.sistema.examenes.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String perfil;
	private Boolean enabled = true;
	
	//la propiedad 'cascade = CascadeType.ALL' permite que si se realiza una modificacion o eliminación en esta entidad Usuario, la tabla relacionada con está entidad 'Usuario' tambien se verá afectada. 
	//Por ejemplo, si se elimina un usuario, todos sus roles también serán eliminados.
	//En la propiedad 'fetch', le colocamos FetchType.EAGER para que sea de tipo EAGER ya que por defecto la anotacion @OneToMany. Sin embargo, se le está colocado de tipo EAGER
	//PARA QUE AL MOMENTO EN EL QUE SE DESEE LISTAR A UN USUARIO PUEDA DEVOLVERNOS TODOS SUS DATOS (LAS CUALES TAMBIEN ESTA RELACIONADO CON OTRAS ENTIDADES COMO PUEDEN SER SUS ROLES O EXAMENES) DE FORMA INMEDIATA
	//En la propiedad 'mappedBy' le colocamos "usuario", el cual se encuentra en la entidad 'UsuarioRol', YA QUE ESTE SERÁ EL PROPIETARIO PARA QUE el atributo "usuario" en la clase "UsuarioRol" esté mapeado al campo "usuarioRoles" en la clase "Usuario".
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "usuario")
	//El 'Set<UsuarioRol>' es una coleccion de tipo Usuario Rol el cual va a almacenar todos los roles que tenga el usuario elegido.
	private Set<UsuarioRol> usuarioRoles = new HashSet<>();
	
}



















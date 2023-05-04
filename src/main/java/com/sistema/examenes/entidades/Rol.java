package com.sistema.examenes.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Rol {

	@Id
	private Long rolid;
	private String nombre;
	
	//la propiedad 'cascade = CascadeType.ALL' permite que si se realiza una modificacion o eliminación en esta entidad ROL, la tabla relacionada con está entidad 'Rol' tambien se verá afectada. 
	//Por ejemplo, si se elimina un Rol, tambien se eliminará este rol a todos los usuarios relacionados.
	//En la propiedad 'fetch', le colocamos FetchType.LAZY YA QUE PARA OBTENER ESOS ROLES SE LE VA A LLAMAR A TRAVEZ DE UN METODO Y NO DE FORMA INMEDIATA COMO EL CASO DEL EAGER 
	//En la propiedad 'mappedBy' le colocamos "rol", el cual se encuentra en la entidad 'UsuarioRol', YA QUE ESTE SERÁ EL PROPIETARIO PARA QUE el atributo "rol" en la clase "UsuarioRol" esté mapeado al campo "usuarioRoles" en la clase "Rol".
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
	private Set<UsuarioRol> usuarioRoles = new HashSet<>();
}

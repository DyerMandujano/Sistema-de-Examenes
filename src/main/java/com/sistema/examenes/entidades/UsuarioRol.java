package com.sistema.examenes.entidades;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UsuarioRol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long usuarioRolId;
	
	//UN USUARIO PUEDE TENER VARIOS ROLES
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	//UN ROL PUEDE PERTENECER A VARIOS USUARIOS
	@ManyToOne
	private Rol rol;
}

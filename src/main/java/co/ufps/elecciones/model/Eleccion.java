package co.ufps.elecciones.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleccion {
	private Integer id;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private String cargo;
	private ArrayList<Estatemento> estatementos;
	private ArrayList<Votante> votantes;
	private ArrayList<Candidato> candidatos;
	
	
	public Eleccion(String nombre, Date fechaInicio, Date fechaFin, String cargo) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cargo = cargo;
	}
	
	

}

package co.ufps.elecciones.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidato {
	private Long id;
	private String documento;
	private String nombre;
	private String apellido;
	private Eleccion eleccion;
	private Integer numero;
	private ArrayList<Voto> votos;
	
	
	public Candidato(String documento, String nombre, String apellido, Eleccion eleccion, Integer numero) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.eleccion = eleccion;
		this.numero = numero;
	}
			
}

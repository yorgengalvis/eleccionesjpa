package co.ufps.elecciones.model;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estatemento {

	private Integer id;
	private Eleccion eleccion;
	private String descripcion;
	private ArrayList<Voto> votos;
	
	
	public Estatemento(Eleccion eleccion, String descripcion) {
		super();
		this.eleccion = eleccion;
		this.descripcion = descripcion;
	}
	
	
	
}

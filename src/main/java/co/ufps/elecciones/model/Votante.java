package co.ufps.elecciones.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votante {

	private Integer id;
	private String nombre;
	private String email;
	private String documento;
	private TipoDocumento tipoDocumento;
	private Eleccion eleccion;
	private ArrayList<Voto> votos;
	
	
	public Votante(String nombre, String email, String documento, TipoDocumento tipoDocumento, Eleccion eleccion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.eleccion = eleccion;
	}
	
	
	
	
	
}

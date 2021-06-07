package co.ufps.elecciones.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDocumento {
	private Integer id;
	private String descripcion;
	private ArrayList<Votante> votantes;
	
	public TipoDocumento(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
		
}

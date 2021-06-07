package co.ufps.elecciones.model;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
	private Integer id;
	private Date fechaCreacion;
	private Date fechaVoto;
	private String uuid;
	private String enlace;
	private Estatemento estatemento;
	private Candidato candidato;
	private Votante votante;
	
	
	public Voto(Date fechaCreacion, Date fechaVoto, String uuid, String enlace) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaVoto = fechaVoto;
		this.uuid = uuid;
		this.enlace = enlace;
	}
	
	
	
}

package co.ufps.elecciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="voto")
public class Voto implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="fechacreacion")
	private Date fechaCreacion;
	@Column(name="fechavoto")
	private Date fechaVoto;
	@Column(name="uuid")
	private String uuid;
	@Column(name="enlace")
	private String enlace;
	
	@ManyToOne
	@JoinColumn(name="estatemento")
	private Estatemento estatemento;
	
	@ManyToOne
	@JoinColumn(name="candidato")
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(name="votante")
	private Votante votante;
	
	
	public Voto(Date fechaCreacion, Date fechaVoto, String uuid, String enlace) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.fechaVoto = fechaVoto;
		this.uuid = uuid;
		this.enlace = enlace;
	}
	
	
	
}

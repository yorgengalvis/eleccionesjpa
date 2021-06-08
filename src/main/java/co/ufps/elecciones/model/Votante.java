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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="votante")
public class Votante implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="email")
	private String email;
	@Column(name="documento")
	private String documento;
	
	@ManyToOne
	@JoinColumn(name="tipodocumento")
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name="eleccion")
	private Eleccion eleccion;

	@OneToMany (mappedBy="votante")
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

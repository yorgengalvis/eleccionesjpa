package ufps.edu.co.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
@Entity(name = "votante")
@Table(name = "votante")
public class Votante {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	@Column(name = "documento", nullable = false, length = 20)
	private String documento;
	@ManyToOne
	@JoinColumn(name = "tipodocumento")
	private TipoDocumento tipoDocumento;
	@ManyToOne
	@JoinColumn(name = "eleccion")
	private Eleccion eleccion;
	

	public Votante(String nombre, String email, String documento, TipoDocumento tipoDocumento, Eleccion eleccion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.eleccion = eleccion;
	}
}
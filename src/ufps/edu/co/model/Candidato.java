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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "candidato")
@Table(name = "candidato")
public class Candidato {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "documento", nullable = false, length = 15)
	private String documento;
	@Column (name = "nombre", nullable = false, length = 15)
	private String nombre;
	@Column (name = "apellido", nullable = false, length = 15)
	private String apellido;
	@ManyToOne
	@JoinColumn(name = "eleccion")
	private Eleccion eleccion;
	@Column (name = "numero", nullable = false, length = 15)
	private Integer numero;
	
	public Candidato() {
		super();
	}
	
	public Candidato(String documento, String nombre, String apellido, Eleccion eleccion, Integer numero) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.eleccion = eleccion;
		this.numero = numero;
	}
	
}
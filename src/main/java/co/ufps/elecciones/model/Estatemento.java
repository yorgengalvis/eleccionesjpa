package co.ufps.elecciones.model;

import java.io.Serializable;
import java.util.ArrayList;

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
@Table(name="estatemento")
public class Estatemento implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="eleccion")
	private Eleccion eleccion;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@OneToMany (mappedBy="estatemento")
	private ArrayList<Voto> votos;
	
	
	public Estatemento(Eleccion eleccion, String descripcion) {
		super();
		this.eleccion = eleccion;
		this.descripcion = descripcion;
	}
	
	
	
}

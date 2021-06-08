package co.ufps.elecciones.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="eleccion")
public class Eleccion implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="fechainicio")
	private Date fechaInicio;
	@Column(name="fechafin")
	private Date fechaFin;
	@Column(name="cargo")
	private String cargo;
	
	@OneToMany (mappedBy="eleccion")
	private ArrayList<Estatemento> estatementos;
	@OneToMany (mappedBy="eleccion")
	private ArrayList<Votante> votantes;
	@OneToMany (mappedBy="eleccion")
	private ArrayList<Candidato> candidatos;
	
	
	public Eleccion(String nombre, Date fechaInicio, Date fechaFin, String cargo) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cargo = cargo;
	}
	
	

}

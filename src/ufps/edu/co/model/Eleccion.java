package ufps.edu.co.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="eleccion")
@Table(name = "eleccion")
public class Eleccion {
	@Id
	@GeneratedValue
	private Integer id;
	@Column (name = "nombre", nullable = false, length = 200)
	private String nombre;
	@Column (name = "fechainicio", nullable = false)
	private Timestamp fechaInicio;
	@Column (name = "fechafin", nullable = false)
	private Timestamp fechaFin;
	@Column (name = "cargo", nullable = false, length = 50)
	private String cargo;
	
	public Eleccion() {
		super();
	}
	public Eleccion(String nombre, Timestamp fechaInicio, Timestamp fechaFin, String cargo) {
		super();
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cargo = cargo;
	}
}
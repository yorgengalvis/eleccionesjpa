package ufps.edu.co.model;

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
@Entity(name = "tipodocumento")
@Table(name = "tipodocumento")
public class TipoDocumento {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "descripcion", nullable = false, length = 50)
	private String descripcion;
}
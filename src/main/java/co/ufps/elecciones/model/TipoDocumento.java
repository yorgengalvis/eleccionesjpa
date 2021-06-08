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
@Table(name="tipodocumento")
public class TipoDocumento implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="descripcion")
	private String descripcion;
	
	@OneToMany (mappedBy="tipoDocumento")
	private ArrayList<Votante> votantes;
	
	public TipoDocumento(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
		
}

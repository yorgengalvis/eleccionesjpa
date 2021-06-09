package ufps.edu.co.model;

import java.sql.Timestamp;

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
@Entity(name = "voto")
@Table(name = "voto")
public class Voto {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "fechacreacion", nullable = true)
	private Timestamp fechaCreacion;
	@Column(name = "fechavoto", nullable = true)
	private Timestamp fechaVoto;
	@Column(name = "uuid", nullable = false, length = 50)
	private String uuid;
	@Column(name = "enlace", nullable = false, length = 50)
	private String enlace;
	@ManyToOne
	@JoinColumn(name = "estamento")
	private Estamento estamento;
	@ManyToOne
	@JoinColumn(name = "candidato")
	private Candidato candidato;
	@ManyToOne
	@JoinColumn(name = "votante")
	private Votante votante;
}
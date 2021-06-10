package ufps.edu.co.dao;

import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;

import ufps.edu.co.model.Candidato;

public class CandidatoDAO extends Conexion<Candidato> implements GenericDAO<Candidato> {

	public CandidatoDAO() {
		super(Candidato.class);
	}
}

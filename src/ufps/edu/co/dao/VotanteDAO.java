package ufps.edu.co.dao;
import ufps.edu.co.util.GenericDAO;
import ufps.edu.co.model.Votante;
import ufps.edu.co.util.Conexion;

public class VotanteDAO extends Conexion<Votante> implements GenericDAO<Votante> {
	public VotanteDAO() {
		super(Votante.class);
	}
}

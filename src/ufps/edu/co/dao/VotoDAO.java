package ufps.edu.co.dao;
import ufps.edu.co.model.Voto;
import ufps.edu.co.util.Conexion;
import ufps.edu.co.util.GenericDAO;


public class VotoDAO extends Conexion<Voto> implements GenericDAO<Voto>{
	public VotoDAO() {
		super(Voto.class);
	}
}

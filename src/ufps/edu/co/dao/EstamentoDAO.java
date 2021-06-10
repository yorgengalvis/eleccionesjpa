package ufps.edu.co.dao;

import ufps.edu.co.model.Estamento;
import ufps.edu.co.util.GenericDAO;
import ufps.edu.co.util.Conexion;

public class EstamentoDAO extends Conexion<Estamento> implements GenericDAO<Estamento>{
	public EstamentoDAO() {
		super(Estamento.class);
	}
}

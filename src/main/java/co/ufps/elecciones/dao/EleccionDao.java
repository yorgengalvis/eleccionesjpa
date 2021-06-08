package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.Eleccion;

public interface EleccionDao {

void saveEleccion(Eleccion Eleccion);
	
	void deleteEleccionById(Integer id);
	
	void updateEleccion(Eleccion Eleccion);
	
	List<Eleccion> findAllEleccion();
	
	Eleccion findById(Integer id);
	
}

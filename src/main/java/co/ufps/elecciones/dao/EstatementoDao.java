package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.Estatemento;

public interface EstatementoDao {
	
void saveEstatemento(Estatemento Estatemento);
	
	void deleteEstatementoById(Integer id);
	
	void updateEstatemento(Estatemento Estatemento);
	
	List<Estatemento> findAllEstatemento();
	
	Estatemento findById(Integer id);
	
}

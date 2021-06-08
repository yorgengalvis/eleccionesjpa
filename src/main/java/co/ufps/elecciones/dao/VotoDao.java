package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.Voto;

public interface VotoDao {
	
void saveVoto(Voto Voto);
	
	void deleteVotoById(Integer id);
	
	void updateVoto(Voto Voto);
	
	List<Voto> findAllVoto();
	
	Voto findById(Integer id);

}

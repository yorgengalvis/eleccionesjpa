package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.Candidato;
import co.ufps.elecciones.model.Votante;

public interface VotanteDao {

void saveVotante(Votante votante);
	
	void deleteVotanteById(Integer id);
	
	void updateVotante(Votante votante);
	
	List<Votante> findAllVotante();
	
	Votante findById(Integer id);
	
}

package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.Candidato;

public interface CandidatoDao {


	void saveCandidato(Candidato candidato);
	
	void deleteCandidatoById(Integer id);
	
	void updateCandidato(Candidato candidato);
	
	List<Candidato> findAllCandidatos();
	
	Candidato findById(Integer id);
	
}

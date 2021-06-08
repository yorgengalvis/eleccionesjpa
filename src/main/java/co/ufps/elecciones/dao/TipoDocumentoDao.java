package co.ufps.elecciones.dao;

import java.util.List;

import co.ufps.elecciones.model.TipoDocumento;

public interface TipoDocumentoDao {
	
void saveTipoDocumento(TipoDocumento TipoDocumento);
	
	void deleteTipoDocumentoById(Integer id);
	
	void updateTipoDocumento(TipoDocumento TipoDocumento);
	
	List<TipoDocumento> findAllTipoDocumento();
	
	TipoDocumento findById(Integer id);
	
}

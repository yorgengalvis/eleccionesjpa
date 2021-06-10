package ufps.edu.co.dao;

import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.util.*;

public class TipoDocumentoDAO extends Conexion<TipoDocumento> implements GenericDAO<TipoDocumento>{
	public TipoDocumentoDAO() {
		super(TipoDocumento.class);
	}
}

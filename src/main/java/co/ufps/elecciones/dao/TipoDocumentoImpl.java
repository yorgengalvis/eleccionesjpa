package co.ufps.elecciones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.elecciones.model.Estatemento;
import co.ufps.elecciones.model.TipoDocumento;
import co.ufps.elecciones.util.ConexionPostgreSql;

public class TipoDocumentoImpl implements TipoDocumentoDao{

private ConexionPostgreSql conexion;
	
	private static final String INSERT_TIPODOCUMENTO_SQL = "INSERT INTO tipodocumento (descripcion) VALUES (? );";
	private static final String DELETE_TIPODOCUMENTO_SQL = "DELETE FROM tipodocumento WHERE id = ?;";
	private static final String UPDATE_TIPODOCUMENTO_SQL = "UPDATE tipodocumento SET  descripcion = ? WHERE id = ?;";
	private static final String SELECT_TIPODOCUMENTO_BY_ID = "SELECT * FROM tipodocumento WHERE id = ?;";
	private static final String SELECT_ALL_TIPODOCUMENTO = "SELECT * FROM tipodocumento;";
	
	
	
	public  TipoDocumentoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}
	
	@Override
	public void saveTipoDocumento(TipoDocumento TipoDocumento) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_TIPODOCUMENTO_SQL);
			preStatement.setString(1, TipoDocumento.getDescripcion());

			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteTipoDocumentoById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_TIPODOCUMENTO_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateTipoDocumento(TipoDocumento TipoDocumento) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_TIPODOCUMENTO_SQL);
			preStatement.setString(1, TipoDocumento.getDescripcion());
			preStatement.setInt(2,TipoDocumento.getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
	}

	@Override
	public List<TipoDocumento> findAllTipoDocumento() {
		ArrayList <TipoDocumento> users = new ArrayList<>();
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_TIPODOCUMENTO);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("idDocumento");
				
				String desc = rs.getString("descripcionEstatemento");
				
				users.add(new TipoDocumento(id, desc, null));
			}
		}catch (SQLException e) {}
		return users;
	}

	@Override
	public TipoDocumento findById(Integer id) {
		TipoDocumento documento = null;
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_TIPODOCUMENTO_BY_ID);
			preStatement.setInt(1, id); 
			ResultSet rs = conexion.query();
			while(rs.next()){
				//obtener eleccion
				String desc = rs.getString("descripcionEstatemento");
				
				documento = new TipoDocumento(id, desc, null);
			}
		}catch (SQLException e) {}
		return documento;
	}

}

package co.ufps.elecciones.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.ufps.elecciones.model.Votante;
import co.ufps.elecciones.util.ConexionPostgreSql;

public class VotanteDaoImpl implements VotanteDao{

private ConexionPostgreSql conexion;
	
	private static final String INSERT_VOTANTE_SQL = "INSERT INTO votante (nombre, email, documento, tipodocumento, eleccion) VALUES (?,?,?,?,? );";
	private static final String DELETE_VOTANTE_SQL = "DELETE FROM votante WHERE id = ?;";
	private static final String UPDATE_VOTANTE_SQL = "UPDATE votante SET  nombre = ?, email = ?, documento = ? , tipodocumento = ? , eleccion = ?  WHERE id = ?;";
	private static final String SELECT_VOTANTE_BY_ID = "SELECT * FROM votante WHERE id = ?;";
	private static final String SELECT_ALL_VOTANTE = "SELECT * FROM votante;";
	
	
	
	public  VotanteDaoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}
	
	@Override
	public void saveVotante(Votante votante) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_VOTANTE_SQL);
			preStatement.setString(1, votante.getNombre());
			preStatement.setString(2,  votante.getEmail());
			preStatement.setString(3, votante.getDocumento());
			preStatement.setInt(4, votante.getTipoDocumento().getId());
			preStatement.setInt(5, votante.getEleccion().getId());

			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteVotanteById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_VOTANTE_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateVotante(Votante votante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Votante> findAllVotante() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Votante findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

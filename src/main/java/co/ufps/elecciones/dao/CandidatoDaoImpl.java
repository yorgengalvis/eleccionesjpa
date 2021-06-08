package co.ufps.elecciones.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.elecciones.model.Candidato;
import co.ufps.elecciones.model.Eleccion;
import co.ufps.elecciones.util.ConexionPostgreSql;


public class CandidatoDaoImpl implements CandidatoDao{

private ConexionPostgreSql conexion;
	
	private static final String INSERT_CANDIDATO_SQL = "INSERT INTO candidato (documento, nombre, apellido, eleccion ,numero) VALUES (?, ?, ? ,?, ? );";
	private static final String DELETE_CANDIDATO_SQL = "DELETE FROM candidato WHERE id = ?;";
	private static final String UPDATE_CANDIDATO_SQL = "UPDATE candidato SET  documento = ?, nombre = ?, apellido = ?,  eleccion = ?,  numero = ? WHERE id = ?;";
	private static final String SELECT_CANDIDATO_BY_ID = "SELECT * FROM candidato WHERE id = ?;";
	private static final String SELECT_ALL_CANDIDATOS = "SELECT * FROM candidato;";
	
	public CandidatoDaoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}
	
	@Override
	public void saveCandidato(Candidato candidato) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_CANDIDATO_SQL);
			preStatement.setString(1, candidato.getDocumento());
			preStatement.setString(2, candidato.getNombre());
			preStatement.setString(3, candidato.getApellido());
			preStatement.setInt(4, candidato.getEleccion().getId());
			preStatement.setInt(5, candidato.getNumero());

			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteCandidatoById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_CANDIDATO_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateCandidato(Candidato candidato) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_CANDIDATO_SQL);
			preStatement.setString(1, candidato.getDocumento());
			preStatement.setString(2, candidato.getNombre());
			preStatement.setString(3, candidato.getApellido());
			preStatement.setInt(4, candidato.getEleccion().getId());
			preStatement.setInt(5, candidato.getNumero());
			preStatement.setInt(6, candidato.getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public List<Candidato> findAllCandidatos() {
		ArrayList <Candidato> users = new ArrayList<>();
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_CANDIDATOS);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("idCandidato");
				String doc = rs.getString("documentoCandidato");
				String nombre = rs.getString("nombreCandidato");
				String apellido = rs.getString("apellidoCandidato");
				
				//falta trar eleccion 
				Eleccion ele = new Eleccion();
							
				int numero = rs.getInt("numeroCandidato");
				users.add(new Candidato(id, doc, nombre, apellido, ele, numero, null));
			}
		}catch (SQLException e) {}
		return users;
	}

	@Override
	public Candidato findById(Integer id) {
		Candidato candidato = null;
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_CANDIDATO_BY_ID);
			preStatement.setInt(1, id); 
			ResultSet rs = conexion.query();
			while(rs.next()){
				String doc = rs.getString("documentoCandidato");
				String nombre = rs.getString("nombreCandidato");
				String apellido = rs.getString("apellidoCandidato");
				
				//falta trar eleccion 
				Eleccion ele = new Eleccion();
							
				int numero = rs.getInt("numeroCandidato");
				candidato = new Candidato(id, doc, nombre, apellido, ele, numero, null);
			}
		}catch (SQLException e) {}
		return candidato;
	}
	
	
}

	


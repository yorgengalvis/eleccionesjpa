package co.ufps.elecciones.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.elecciones.model.Eleccion;
import co.ufps.elecciones.model.Eleccion;
import co.ufps.elecciones.model.Eleccion;
import co.ufps.elecciones.util.ConexionPostgreSql;

public class EleccionDaoImpl implements EleccionDao {

private ConexionPostgreSql conexion;
	
	private static final String INSERT_ELECCION_SQL = "INSERT INTO eleccion (nombre, fechainicio, fechafin ,cargo) VALUES (?, ?, ? ,? );";
	private static final String DELETE_ELECCION_SQL = "DELETE FROM eleccion WHERE id = ?;";
	private static final String UPDATE_ELECCION_SQL = "UPDATE eleccion SET  nombre = ?, fechainicio = ?,  fechafin = ?,  cargo = ? WHERE id = ?;";
	private static final String SELECT_ELECCION_BY_ID = "SELECT * FROM eleccion WHERE id = ?;";
	private static final String SELECT_ALL_ELECCION = "SELECT * FROM eleccion;";
	
	
	
	public EleccionDaoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}

	@Override
	public void saveEleccion(Eleccion Eleccion) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_ELECCION_SQL);
			preStatement.setString(1, Eleccion.getNombre());
			preStatement.setDate(2, (Date) Eleccion.getFechaInicio());
			preStatement.setDate(3, (Date) Eleccion.getFechaFin());
			preStatement.setString(4, Eleccion.getCargo());

			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteEleccionById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_ELECCION_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateEleccion(Eleccion Eleccion) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_ELECCION_SQL);
			preStatement.setString(1, Eleccion.getNombre());
			preStatement.setDate(2, (Date) Eleccion.getFechaInicio());
			preStatement.setDate(3, (Date) Eleccion.getFechaFin());
			preStatement.setString(4, Eleccion.getCargo());
			preStatement.setInt(5, Eleccion.getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public List<Eleccion> findAllEleccion() {
		ArrayList <Eleccion> users = new ArrayList<>();
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_ELECCION);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("idEleccion");
				String nombre = rs.getString("nombreEleccion");
				Date inicio = rs.getDate("fechaInicioEleccion");
				Date fin = rs.getDate("fechaFinEleccion");
				String cargo = rs.getString("cargoEleccion");
				
				//falta trar eleccion 
				Eleccion ele = new Eleccion();
							
				int numero = rs.getInt("numeroEleccion");
				users.add(new Eleccion(id, nombre, inicio, fin, cargo, null, null, null));
			}
		}catch (SQLException e) {}
		return users;
	}

	@Override
	public Eleccion findById(Integer id) {
		Eleccion eleccion = null;
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ELECCION_BY_ID);
			preStatement.setInt(1, id); 
			ResultSet rs = conexion.query();
			while(rs.next()){
				String nombre = rs.getString("nombreEleccion");
				Date inicio = rs.getDate("fechaInicioEleccion");
				Date fin = rs.getDate("fechaFinEleccion");
				String cargo = rs.getString("cargoEleccion");
				
				eleccion = new Eleccion(id, nombre, inicio, fin, cargo, null, null, null);
			}
		}catch (SQLException e) {}
		return eleccion;
	}

}

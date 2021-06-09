
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.elecciones.model.Candidato;
import co.ufps.elecciones.model.Eleccion;
import co.ufps.elecciones.model.Estatemento;
import co.ufps.elecciones.util.ConexionPostgreSql;


public class EstatementoDaoImpl implements EstatementoDao{

private ConexionPostgreSql conexion;
	
	private static final String INSERT_ESTATEMENTO_SQL = "INSERT INTO estatemento (eleccion,descripcion) VALUES (? ,? );";
	private static final String DELETE_ESTATEMENTO_SQL = "DELETE FROM estatemento WHERE id = ?;";
	private static final String UPDATE_ESTATEMENTO_SQL = "UPDATE estatemento SET  eleccion = ?,  descripcion = ? WHERE id = ?;";
	private static final String SELECT_ESTATEMENTO_BY_ID = "SELECT * FROM estatemento WHERE id = ?;";
	private static final String SELECT_ALL_ESTATEMENTO = "SELECT * FROM estatemento;";
	
	
	
	public  EstatementoDaoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}
		
	
	
	@Override
	public void saveEstatemento(co.ufps.elecciones.model.Estatemento Estatemento) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_ESTATEMENTO_SQL);
			preStatement.setInt(1, Estatemento.getEleccion().getId());
			preStatement.setString(2, Estatemento.getDescripcion());

			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteEstatementoById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_ESTATEMENTO_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateEstatemento(Estatemento Estatemento) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_ESTATEMENTO_SQL);
			preStatement.setInt(1, Estatemento.getEleccion().getId());
			preStatement.setString(2, Estatemento.getDescripcion());
			preStatement.setInt(3, Estatemento.getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public List<Estatemento> findAllEstatemento() {
		ArrayList <Estatemento> users = new ArrayList<>();
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_ESTATEMENTO);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("id");

				int idelec = rs.getInt("eleccion");
				String desc = rs.getString("descripcion");
				
				EleccionDaoImpl eleccion = new EleccionDaoImpl();
				Eleccion ele = eleccion.findById(idelec);
	
				//falta array
				users.add(new Estatemento(id, ele, desc, null));
			}
		}catch (SQLException e) {}
		return users;
	}

	@Override
	public Estatemento findById(Integer id) {
		Estatemento estatemento = null;
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ESTATEMENTO_BY_ID);
			preStatement.setInt(1, id); 
			ResultSet rs = conexion.query();
			while(rs.next()){
				int idelec = rs.getInt("eleccion");
				String desc = rs.getString("descripcion");
				
				EleccionDaoImpl eleccion = new EleccionDaoImpl();
				Eleccion ele = eleccion.findById(idelec);
	
				//falta array
				estatemento = new Estatemento(id, ele, desc, null);
			}
		}catch (SQLException e) {}
		return estatemento;
	}

}

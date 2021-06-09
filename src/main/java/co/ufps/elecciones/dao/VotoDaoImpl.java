package co.ufps.elecciones.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.ufps.elecciones.model.Candidato;
import co.ufps.elecciones.model.Estatemento;
import co.ufps.elecciones.model.Votante;
import co.ufps.elecciones.model.Voto;
import co.ufps.elecciones.util.ConexionPostgreSql;

public class VotoDaoImpl implements VotoDao{

private ConexionPostgreSql conexion;
	
	private static final String INSERT_VOTO_SQL = "INSERT INTO voto (fechacreacion, fechavoto, uuid, enlace, estatemento, candidato ,votante) VALUES (?,?,?,?,?,?,? );";
	private static final String DELETE_VOTO_SQL = "DELETE FROM voto WHERE id = ?;";
	private static final String UPDATE_VOTO_SQL = "UPDATE voto SET  fechacreacion = ?, fechavoto = ?, uuid = ? , enlace = ? , estatemento = ? , candidato = ? , votante = ?  WHERE id = ?;";
	private static final String SELECT_VOTO_BY_ID = "SELECT * FROM voto WHERE id = ?;";
	private static final String SELECT_ALL_VOTO = "SELECT * FROM voto;";
	
	
	
	public  VotoDaoImpl() {
		this.conexion =ConexionPostgreSql.getConexion();
	}
	
	@Override
	public void saveVoto(Voto Voto) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(INSERT_VOTO_SQL);
			preStatement.setDate(1, (Date) Voto.getFechaCreacion());
			preStatement.setDate(2, (Date) Voto.getFechaVoto());
			preStatement.setString(3, Voto.getUuid());
			preStatement.setString(4, Voto.getEnlace());
			preStatement.setInt(5, Voto.getEstatemento().getId());
			preStatement.setInt(6, Voto.getCandidato().getId());
			preStatement.setInt(7, Voto.getVotante().getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public void deleteVotoById(Integer id) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_VOTO_SQL);
			preStatement.setInt(1, id); 
			conexion.execute();
		}catch (SQLException e) {}
		
	}

	@Override
	public void updateVoto(Voto Voto) {
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(UPDATE_VOTO_SQL);
			preStatement.setDate(1, (Date) Voto.getFechaCreacion());
			preStatement.setDate(2, (Date) Voto.getFechaVoto());
			preStatement.setString(3, Voto.getUuid());
			preStatement.setString(4, Voto.getEnlace());
			preStatement.setInt(5, Voto.getEstatemento().getId());
			preStatement.setInt(6, Voto.getCandidato().getId());
			preStatement.setInt(7, Voto.getVotante().getId());
			preStatement.setInt(8, Voto.getId());
			
			conexion.execute();
			
		}catch (SQLException e) {}
		
	}

	@Override
	public List<Voto> findAllVoto() {
		ArrayList <Voto> users = new ArrayList<>();
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_VOTO);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				int id = rs.getInt("id");
				
				Date creacion = rs.getDate("fechacreacion");
				Date fechaVoto = rs.getDate("fechavoto");
				String uuid = rs.getString("uuidV");
				String enlace = rs.getString("enlace");
				
				
				int idestatemento = rs.getInt("estatemento");
				EstatementoDaoImpl estatemento = new EstatementoDaoImpl();
				Estatemento esta = estatemento.findById(idestatemento);
				
				int idcandidato = rs.getInt("candidato");
				CandidatoDaoImpl candidato = new CandidatoDaoImpl();
				Candidato cand = candidato.findById(idcandidato);
				
				int idvotante = rs.getInt("votantante");
				VotanteDaoImpl votante = new VotanteDaoImpl();
				Votante vot = votante.findById(idvotante);
				
				users.add(new Voto(id, creacion, fechaVoto, uuid, enlace, esta, cand, vot));
			}
		}catch (SQLException e) {}
		return users;
	}

	@Override
	public Voto findById(Integer id) {
		Voto voto = null;
		try {
			PreparedStatement preStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_VOTO_BY_ID);
			preStatement.setInt(1, id); 
			ResultSet rs = conexion.query();
			while(rs.next()){
				Date creacion = rs.getDate("fechacreacion");
				Date fechaVoto = rs.getDate("fechavoto");
				String uuid = rs.getString("uuidV");
				String enlace = rs.getString("enlace");
				
				
				int idestatemento = rs.getInt("estatemento");
				EstatementoDaoImpl estatemento = new EstatementoDaoImpl();
				Estatemento esta = estatemento.findById(idestatemento);
				
				int idcandidato = rs.getInt("candidato");
				CandidatoDaoImpl candidato = new CandidatoDaoImpl();
				Candidato cand = candidato.findById(idcandidato);
				
				int idvotante = rs.getInt("votantante");
				VotanteDaoImpl votante = new VotanteDaoImpl();
				Votante vot = votante.findById(idvotante);
				
				voto = new Voto(id, creacion, fechaVoto, uuid, enlace, esta, cand, vot);
			}
		}catch (SQLException e) {}
		return voto;
	}
}

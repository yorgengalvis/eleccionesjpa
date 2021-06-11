package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.EleccionDAO;
import ufps.edu.co.dao.EstamentoDAO;
import ufps.edu.co.dao.TipoDocumentoDAO;
import ufps.edu.co.dao.VotanteDAO;
import ufps.edu.co.dao.VotoDAO;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Estamento;
import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.model.Votante;
import ufps.edu.co.model.Voto;
import ufps.edu.co.util.EnviarEmail;


@WebServlet("/votantes")
public class VotantesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private VotanteDAO voDAO;
    private TipoDocumentoDAO tpDAO;
    private EleccionDAO eDAO;
    private EstamentoDAO estamentoDAO;
    private VotoDAO vdao;
    
    public VotantesController() {
        super();
        voDAO=new VotanteDAO();
        tpDAO=new TipoDocumentoDAO();
        eDAO=new EleccionDAO();
        estamentoDAO=new EstamentoDAO();
        vdao=new VotoDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action==null) {
			try {
			listado(request,response);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}else{
			
			try {
				
				switch(action) {
		 			case "delete":delete(request,response);
						break;
		 			case "edit":edit(request,response);
		 			break;
					default: 
						listado(request,response);
						break;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
		}
	
	}
	
	private void edit(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
			Integer id=Integer.parseInt(request.getParameter("id"));
			Votante v=voDAO.find(id);
			request.setAttribute("votante", v);
			List<Votante> listado=voDAO.list();
			List<TipoDocumento> tiposDocumentos=tpDAO.list();
			List<Estamento> listadoEstamentos=estamentoDAO.list();
			request.setAttribute( "listado",listado);
			request.setAttribute("listadoEstamentos", listadoEstamentos);
			request.setAttribute("tiposDocumentos", tiposDocumentos);
			RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/index.jsp");
			dispatcher.forward(request,response);
	}

	private void delete(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
				Integer id=Integer.parseInt(request.getParameter("id"));
				Votante v=voDAO.find(id);
				voDAO.delete(v);
				response.sendRedirect("votantes");
	}
	
	private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<Votante> listado=voDAO.list();
		List<TipoDocumento> tiposDocumentos=tpDAO.list();
		List<Estamento> listadoEstamentos=estamentoDAO.list();
		request.setAttribute( "listado",listado);
		request.setAttribute("listadoEstamentos", listadoEstamentos);
		request.setAttribute("tiposDocumentos", tiposDocumentos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/index.jsp");
		dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.print("Entro por POST");
		try {
			
				switch(action) {
		 			case "add": registrar(request,response);
						break;
		 			case "update": update(request,response);
		 			    break;
					default: 
						listado(request,response);
						break;
				}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}
	
	private void registrar(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{

		Integer idestamento=Integer.parseInt(request.getParameter("estamento"));
		Integer idtipodocumento=Integer.parseInt(request.getParameter("tipodocumento"));
		String documento=request.getParameter("documento");
		String nombre=request.getParameter("nombre");
		String email=request.getParameter("email");
		Estamento est=estamentoDAO.find(idestamento);
		TipoDocumento tpd=tpDAO.find(idtipodocumento);
		Votante v=new Votante(nombre,email,documento,tpd,est.getEleccion());
		this.voDAO.insert(v);
		
		String enlace = UUID.randomUUID().toString();
		String uuid = UUID.randomUUID().toString().split("-")[0];
		
		Voto voto=new Voto();
		voto.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		voto.setVotante(v);
		voto.setEnlace(enlace);
		voto.setUuid(uuid);
		voto.setEstamento(est);
		
		this.vdao.insert(voto);
		
		
		EnviarEmail serviceMail=new EnviarEmail("progwebtesteos@gmail.com","Programacion2021");
		String receptor=v.getEmail();
		String url="http://40.84.138.56:8080/EleccionesUFPS2021/votar?action=validar&token="+enlace;
		String asunto="Proceso de Consulta "+est.getEleccion().getNombre();
		String cuerpo="Estimado <strong>"+v.getNombre()+"</strong>,"+ 
				"<p>Se le ha creado usuario para el proceso de consulta <strong>"+est.getEleccion().getNombre()+"</strong>, por lo que a continuaciòn apareceran sus datos de ingreso a la plataforma:</p>"
				+"<p><b>Estamento:</b>"+est.getDescripcion()+"<br><strong>Fecha de Inicio Consulta: </strong>"+est.getEleccion().getFechaInicio()+"<br><strong>Fecha de Fin Consulta: "+est.getEleccion().getFechaFin()+"</strong>"
				+ "<br><b>Su contraseña de ingreso:</b>"+uuid+"</b><br>Atravez del siguiente <a href='"+url+"'>Enlace</a>"+" podrà realizar el registro de su voto.</p>"
						+ "<br><br><br><br>"
						+ "Equipo Programaciòn WEB 2021";
		serviceMail.sendEmail(receptor, asunto, cuerpo);
		
		response.sendRedirect("votantes");
	}
	
	
	
	private void update(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Integer idtipodocumento=Integer.parseInt(request.getParameter("tipodocumento"));
		TipoDocumento tpd=tpDAO.find(idtipodocumento);
		Votante v=voDAO.find(id);
		Integer idestamento=Integer.parseInt(request.getParameter("estamento"));
		Estamento es=estamentoDAO.find(idestamento);
		Eleccion e=eDAO.find(es.getEleccion().getId());
		String documento=request.getParameter("documento");
		String nombre=request.getParameter("nombre");
		String email=request.getParameter("email");
		
		v.setDocumento(documento);
		v.setNombre(nombre);
		v.setEmail(email);
		v.setEleccion(e);
		v.setTipoDocumento(tpd);
		
		this.voDAO.update(v);
		response.sendRedirect("votantes");
	}

}

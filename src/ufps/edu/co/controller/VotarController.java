package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.TipoDocumentoDAO;
import ufps.edu.co.dao.VotanteDAO;
import ufps.edu.co.dao.VotoDAO;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.model.Votante;
import ufps.edu.co.model.Voto;

/**
 * Servlet implementation class VotarController
 */
@WebServlet("/votar")
public class VotarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private VotoDAO vdao;
	private TipoDocumentoDAO tpdao;
	private VotanteDAO vtdao;
	
	private boolean existsSesion;
    
    public VotarController() {
        super();
        vdao=new VotoDAO();
        tpdao=new TipoDocumentoDAO();
        vtdao=new VotanteDAO();
        this.existsSesion=false;
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.print("Accion: "+action);
			try {
				switch(action) {
		 			case "validar": validar(request,response);
						break;
		 			case "show"://show(request,response);
		 			break;
					default: 
						//listado(request,response);
						break;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
		
	}

	private Voto selectedVoto=null;
	
	private void validar(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{

		List<Voto> votos=vdao.list();
		Voto selected=null;
		String enlace=request.getParameter("token");
		for(Voto recorre:votos) {
			if(recorre.getEnlace().equals(enlace)) {
				selected=recorre;
			}
		}
		if(selected.getFechaVoto()!=null)
		{
			request.setAttribute("voto", selected);
			request.setAttribute("votante", selected.getVotante());
			RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/yavoto.jsp");
			dispatcher.forward(request,response);
		}else {
			this.selectedVoto=selected;
			List<TipoDocumento> listadotp=tpdao.list();
			request.setAttribute("tiposDocumentos", listadotp);
			request.setAttribute("votante", selected.getVotante());
			this.existsSesion=true;
			//request.setAttribute("listaElecciones", listadooe);
			RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/confirmaDatos.jsp");
			dispatcher.forward(request,response);
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.print("POST Accion: "+action);
			try {
				switch(action) {
		 			case "confirmData": confirmarDatos(request,response);
						break;		
						default:
							break;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		
	}
	
	private void confirmarDatos(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Votante v=vtdao.find(id);
		Integer idtipodocumento=Integer.parseInt(request.getParameter("tipodocumento"));
		TipoDocumento tp=tpdao.find(idtipodocumento);
		String documento=request.getParameter("documento");
		String uuid=request.getParameter("token");
		
		String ruta="votantes/error_votar.jsp";
		System.out.print("UUID: "+this.selectedVoto.getUuid());
		if(v.getDocumento().equals(documento) && v.getTipoDocumento().getId()==tp.getId() && this.selectedVoto.getUuid().equals(uuid)) {
			request.setAttribute("votante", v);
			ruta="votantes/votar.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ruta);
		dispatcher.forward(request,response);
		
	}

}

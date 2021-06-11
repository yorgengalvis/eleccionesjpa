package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.CandidatoDAO;
import ufps.edu.co.dao.TipoDocumentoDAO;
import ufps.edu.co.dao.VotanteDAO;
import ufps.edu.co.dao.VotoDAO;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.TipoDocumento;
import ufps.edu.co.model.Votante;
import ufps.edu.co.model.Voto;
import ufps.edu.co.util.ValidarCaptcha;

/**
 * Servlet implementation class VotarController
 */
@WebServlet("/votar")
public class VotarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private VotoDAO vdao;
	private TipoDocumentoDAO tpdao;
	private VotanteDAO vtdao;
	private CandidatoDAO cdao;
	private Voto selectedVoto=null;
	private Votante votador=null;
	
	private boolean existsSesion;
    
    public VotarController() {
        super();
        vdao=new VotoDAO();
        tpdao=new TipoDocumentoDAO();
        vtdao=new VotanteDAO();
        this.existsSesion=false;
        cdao=new CandidatoDAO();
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
		 			case "selected": selected(request,response);
		 			break;
		 			case "exito" : exito(request,response);
		 			break;
					default: 
						//listado(request,response);
						break;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			
		
	}
	
	private void exito(HttpServletRequest request,HttpServletResponse response ) throws ServletException, SQLException, IOException{
		request.setAttribute("votante", this.votador);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/exitoso.jsp");
		dispatcher.forward(request,response);
	}
		
	
	private void selected(HttpServletRequest request,HttpServletResponse response ) throws ServletException, SQLException, IOException{
		Integer idcandidato=Integer.parseInt(request.getParameter("candidate"));
		Candidato c=cdao.find(idcandidato);
		request.setAttribute("voto", this.selectedVoto);
		request.setAttribute("candidato",c);
		request.setAttribute("eleccion", this.selectedVoto.getEstamento().getEleccion());
		request.setAttribute("votante", this.votador);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/selectedVote.jsp");
		dispatcher.forward(request,response);
	}

	
	
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
			this.votador=selected.getVotante();
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
		 			case "confirmedVoto": confirmedVoto(request,response);
		 			break;
						default:
							break;
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		
	}
	private void confirmedVoto(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer idcandidato=Integer.parseInt(request.getParameter("candidate"));
		Candidato c=cdao.find(idcandidato);
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verificado = ValidarCaptcha.verificar(gRecaptchaResponse);
		Integer idvoto=Integer.parseInt(request.getParameter("votoid"));
		
		if(verificado) {
			Voto v=vdao.find(idvoto);
			v.setCandidato(c);
			v.setFechaVoto(new Timestamp(System.currentTimeMillis()));
			vdao.update(v);
			String ruta="votar?action=exito";
			response.sendRedirect(ruta);
		}else{
			response.sendRedirect("selected");
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
		
		if(v.getEleccion().getFechaFin().before(new Timestamp(System.currentTimeMillis()))){
			ruta="votantes/sepasoeltiempo.jsp";
		}else if(v.getDocumento().equals(documento) && v.getTipoDocumento().getId()==tp.getId() && this.selectedVoto.getUuid().equals(uuid)) {
			List<Candidato> candidatos=cdao.list();
			request.setAttribute("candidatos", candidatos);
			request.setAttribute("voto", this.selectedVoto);
			request.setAttribute("votante", v);
			ruta="votantes/votar.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(ruta);
		dispatcher.forward(request,response);
		
	}

}

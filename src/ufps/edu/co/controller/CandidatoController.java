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

import ufps.edu.co.dao.CandidatoDAO;
import ufps.edu.co.dao.EleccionDAO;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Estamento;

/**
 * Servlet implementation class CandidatoController
 */
@WebServlet("/candidatos")
public class CandidatoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CandidatoDAO cDAO;
	private EleccionDAO edao;
	
	
    public CandidatoController() {
        super();
        cDAO=new CandidatoDAO();
        edao=new EleccionDAO();
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
		Candidato cn=cDAO.find(id);
		List<Candidato> listado=cDAO.list();
		request.setAttribute( "listado",listado);
		request.setAttribute("candidato", cn);
		List<Eleccion> listadooe=edao.list();
		request.setAttribute("listaElecciones", listadooe);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidatos/index.jsp");
		dispatcher.forward(request,response);
	}
	
	private void delete(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
				Integer id=Integer.parseInt(request.getParameter("id"));
				Candidato t=cDAO.find(id);
				cDAO.delete(t);
				response.sendRedirect("candidatos");
	}

	private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<Candidato> listado=cDAO.list();
		request.setAttribute( "listado",listado);
		List<Eleccion> listadooe=edao.list();
		request.setAttribute("listaElecciones", listadooe);
		RequestDispatcher dispatcher = request.getRequestDispatcher("candidatos/index.jsp");
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
		String documento=request.getParameter("documento");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		Integer ideleccion=Integer.parseInt(request.getParameter("eleccion"));
		Integer numero=Integer.parseInt(request.getParameter("numero"));
		Eleccion e=edao.find(ideleccion);
		Candidato c=new Candidato(documento,nombre,apellido,e,numero);
		this.cDAO.insert(c);
		response.sendRedirect("candidatos");
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Candidato c=this.cDAO.find(id);
		String documento=request.getParameter("documento");
		String nombre=request.getParameter("nombre");
		String apellido=request.getParameter("apellido");
		
		Integer ideleccion=Integer.parseInt(request.getParameter("eleccion"));
		
		Integer numero=Integer.parseInt(request.getParameter("numero"));
		Eleccion e=edao.find(ideleccion);
	
		c.setDocumento(documento);
		c.setNombre(nombre);
		c.setApellido(apellido);
		c.setEleccion(e);
		c.setNumero(numero);
		
		this.cDAO.update(c);
		response.sendRedirect("candidatos");
	}

}

package ufps.edu.co.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.EleccionDAO;
import ufps.edu.co.dao.EstamentoDAO;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Estamento;

/**
 * Servlet implementation class EstamentoController
 */
@WebServlet("/estamentos")
public class EstamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private EstamentoDAO eDAO;   
    private EleccionDAO eleccionesDAO;
    
    public EstamentoController() {
        super();
        this.eDAO=new EstamentoDAO();
        this.eleccionesDAO=new EleccionDAO();
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
		List<Estamento> listado=eDAO.list();
		request.setAttribute( "listado",listado);
		Estamento t=eDAO.find(id);
		List<Eleccion> eleccionesList=eleccionesDAO.list();
		request.setAttribute( "estamento",t);
		request.setAttribute( "listaElecciones",eleccionesList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("estamento/index.jsp");
		dispatcher.forward(request,response);
	}

	private void delete(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
				Integer id=Integer.parseInt(request.getParameter("id"));
				Estamento t=eDAO.find(id);
				eDAO.delete(t);
				response.sendRedirect("estamentos");
	}
	
	private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<Estamento> listado=eDAO.list();
		List<Eleccion> eleccionesList=eleccionesDAO.list();
		request.setAttribute( "listado",listado);
		request.setAttribute( "listaElecciones",eleccionesList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("estamento/index.jsp");
		dispatcher.forward(request,response);
	}
	
	
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String descripcion=request.getParameter("descripcion");
		Integer ideleccion=Integer.parseInt(request.getParameter("eleccion"));
		
		Eleccion e=this.eleccionesDAO.find(ideleccion);
		Estamento et=new Estamento();
		et.setEleccion(e);
		et.setDescripcion(descripcion);
		
		this.eDAO.insert(et);
		
		response.sendRedirect("estamentos");
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Estamento e=this.eDAO.find(id);
		String descripcion=request.getParameter("descripcion");
		Integer ideleccion=Integer.parseInt(request.getParameter("eleccion"));
		Eleccion ed=this.eleccionesDAO.find(ideleccion);
		e.setDescripcion(descripcion);
		e.setEleccion(ed);
		this.eDAO.update(e);
		response.sendRedirect("estamentos");
	}
	
}

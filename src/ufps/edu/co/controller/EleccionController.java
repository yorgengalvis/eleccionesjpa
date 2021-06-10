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

import ufps.edu.co.dao.EleccionDAO;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.TipoDocumento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Servlet implementation class EleccionController
 */
@WebServlet("/elecciones")
public class EleccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EleccionDAO eDAO;
    
    public EleccionController() {
        super();
        this.eDAO=new EleccionDAO();
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
		Eleccion t=eDAO.find(id);
		List<Eleccion> listado=eDAO.list();
		request.setAttribute( "listadoElecciones",listado);
		request.setAttribute( "eleccion",t);
		RequestDispatcher dispatcher = request.getRequestDispatcher("eleccion/index.jsp");
		dispatcher.forward(request,response);
	}

	private void delete(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
				Integer id=Integer.parseInt(request.getParameter("id"));
				Eleccion t=eDAO.find(id);
				eDAO.delete(t);
				response.sendRedirect("elecciones");
	}
	
	private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<Eleccion> listado=eDAO.list();
		request.setAttribute( "listadoElecciones",listado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("eleccion/index.jsp");
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
		String nombre = request.getParameter("nombre");
		String [] fechaInicioS = request.getParameter("fechainicio").split("-");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(fechaInicioS[0]),Integer.parseInt(fechaInicioS[1])-1, Integer.parseInt(fechaInicioS[2]));
		Timestamp fechaInicio = new Timestamp(c.getTimeInMillis());
		Calendar x = Calendar.getInstance();
		String [] fechaFinS = request.getParameter("fechafin").split("-");
		x.set(Integer.parseInt(fechaFinS[0]),Integer.parseInt(fechaFinS[1])-1, Integer.parseInt(fechaFinS[2]));
		Timestamp fechaFin = new Timestamp(x.getTimeInMillis());
		String cargo=request.getParameter("cargo");
		Eleccion e=new Eleccion();
		e.setNombre(nombre);
		e.setFechaInicio(fechaInicio);
		e.setFechaFin(fechaFin);
		e.setCargo(cargo);
		this.eDAO.insert(e);
		response.sendRedirect("elecciones");
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		Eleccion elecc = this.eDAO.find(id);
		String [] fechaInicioS = request.getParameter("fechainicio").split("-");
		String nombre = request.getParameter("nombre");
		Calendar c = Calendar.getInstance();
		c.set(Integer.parseInt(fechaInicioS[0]),Integer.parseInt(fechaInicioS[1])-1, Integer.parseInt(fechaInicioS[2]));
		Timestamp fechaInicio = new Timestamp(c.getTimeInMillis());
		Calendar x = Calendar.getInstance();
		String [] fechaFinS = request.getParameter("fechafin").split("-");
		x.set(Integer.parseInt(fechaFinS[0]),Integer.parseInt(fechaFinS[1])-1, Integer.parseInt(fechaFinS[2]));
		Timestamp fechaFin = new Timestamp(x.getTimeInMillis());
		String cargo=request.getParameter("cargo");
		
		elecc.setNombre(nombre);
		elecc.setFechaInicio(fechaInicio);
		elecc.setFechaFin(fechaFin);
		elecc.setCargo(cargo);
		this.eDAO.update(elecc);
		response.sendRedirect("elecciones");
	}

}

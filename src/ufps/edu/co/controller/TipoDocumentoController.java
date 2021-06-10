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
import ufps.edu.co.model.TipoDocumento;

/**
 * Servlet implementation class TipoDocumentoController
 */
@WebServlet("/tipodocumento")
public class TipoDocumentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private TipoDocumentoDAO tpdao;
	
    public TipoDocumentoController() {
        super();
        tpdao=new TipoDocumentoDAO();
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
		 			case "delete": delete(request,response);
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
	
	private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<TipoDocumento> listado=tpdao.list();
		request.setAttribute( "listadoTipos",listado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("tipodocumentos/index.jsp");
		dispatcher.forward(request,response);
	}
	
	private void edit(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		TipoDocumento t=tpdao.find(id);
		List<TipoDocumento> listado=tpdao.list();
		request.setAttribute( "listadoTipos",listado);
		request.setAttribute( "tipodocumento",t);
		RequestDispatcher dispatcher = request.getRequestDispatcher("tipodocumentos/index.jsp");
		dispatcher.forward(request,response);
	}
	
	
	
	private void delete(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
				Integer id=Integer.parseInt(request.getParameter("id"));
				TipoDocumento t=tpdao.find(id);
				tpdao.delete(t);
				response.sendRedirect("tipodocumento");
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
		String descripcion = request.getParameter("descripcion");
		TipoDocumento tpdt = new TipoDocumento();
		tpdt.setDescripcion(descripcion);
		this.tpdao.insert(tpdt);
		response.sendRedirect("tipodocumento");
	}
	
	private void update(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		Integer id=Integer.parseInt(request.getParameter("id"));
		String descripcion=request.getParameter("descripcion");
		TipoDocumento tpdt = this.tpdao.find(id);
		tpdt.setDescripcion(descripcion);
		this.tpdao.update(tpdt);
		response.sendRedirect("tipodocumento");
	}

}

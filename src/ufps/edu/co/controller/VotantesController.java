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

import ufps.edu.co.dao.VotanteDAO;
import ufps.edu.co.model.Candidato;
import ufps.edu.co.model.Eleccion;
import ufps.edu.co.model.Votante;


@WebServlet("/votantes")
public class VotantesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private VotanteDAO voDAO;
    
    public VotantesController() {
        super();
        voDAO=new VotanteDAO();
       
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
		 			case "delete"://delete(request,response);
						break;
		 			case "edit"://edit(request,response);
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
		List<Votante> listado=voDAO.list();
		request.setAttribute( "listado",listado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votantes/index.jsp");
		dispatcher.forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import ufps.edu.co.dao.VotoDAO;
import ufps.edu.co.model.Voto;


@WebServlet("/voto")
public class VotoController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	 
	private VotoDAO vtdao;
	
    public VotoController() {
        super();
        vtdao=new VotoDAO();
    }
    
    
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
					listado(request,response);
	
				} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listado(request,response);
	
		} catch (Exception e) {
		System.out.print(e.getMessage());
}
}
    
    private void listado(HttpServletRequest request,HttpServletResponse response ) 
			throws ServletException, SQLException, IOException{
		List<Voto> listado=vtdao.list();
		request.setAttribute( "listadoVotos",listado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votos/index.jsp");
		dispatcher.forward(request,response);
	}
	
}

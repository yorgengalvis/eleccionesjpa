package ufps.edu.co.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ufps.edu.co.dao.VotoDAO;
import ufps.edu.co.model.Voto;


@WebServlet("/votos")
public class VotosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VotoDAO vtdao;
    public VotosController() {
        super();
        vtdao=new VotoDAO();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Voto> listado=vtdao.list();
		request.setAttribute( "listadoVotos",listado);
		RequestDispatcher dispatcher = request.getRequestDispatcher("votos/index.jsp");
		dispatcher.forward(request,response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.excilys.librarymanager.servlet;

import java.io.IOException;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.model.Membre;
import com.excilys.librarymanager.model.Emprunt;

import com.excilys.librarymanager.service.EmpruntService;
import com.excilys.librarymanager.service.EmpruntServiceImpl;
import com.excilys.librarymanager.servlet.EmpruntListServlet;

public class EmpruntReturnServlet extends HttpServlet {	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
		
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		try{
			emprunts = empruntService.getListCurrent();
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("empruntslistCurrent", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/emprunt_return.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
		String inputEmprunt = request.getParameter("idEmprunt");
        
        int idEmprunt = Integer.parseInt(inputEmprunt);
		try {
			empruntService.returnBook(idEmprunt);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		List<Emprunt> emprunts = new ArrayList<Emprunt>();
		try{
			emprunts = empruntService.getListCurrent();
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/emprunt_list.jsp");
		dispatcher.forward(request, response);
	}
}
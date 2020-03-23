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
import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.servlet.LivreServiceImpl;
import com.excilys.librarymanager.service.MembreService;
import com.excilys.librarymanager.servlet.MembreServiceImpl;

public class EmpruntAddServlet extends HttpServlet {	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MembreServiceImpl membreService = MembreServiceImpl.getInstance();
        LivreServiceImpl livreService = LivreServiceImpl.getInstance();
		
		List<Membre> membres = new ArrayList<Membre>();
		List<Livre> livres = new ArrayList<Livre>();
		try{
            livres = livreService.getListDispo();
            membres = membreService.getList();
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("livres", livres);
		request.setAttribute("membres", membres);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/emprunt_add.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmpruntServiceImpl empruntService = EmpruntServiceImpl.getInstance();
		String inputIdLivre = request.getParameter("idDuLivre");
		String inputIdMembre = request.getParameter("idDuMembre");
        
        int idLivre = Integer.parseInt(inputIdLivre);
        int idMembre = Integer.parseInt(inputIdMembre);
        Emprunt newEmprunt;
		try {
			newEmprunt = empruntService.create(idMembre, idLivre, LocalDate.now());
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
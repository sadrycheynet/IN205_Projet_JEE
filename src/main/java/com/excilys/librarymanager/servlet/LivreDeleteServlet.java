package com.excilys.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.librarymanager.exception.ServiceException;

import com.excilys.librarymanager.model.Livre;

import com.excilys.librarymanager.service.LivreService;
import com.excilys.librarymanager.service.LivreServiceImpl;

public class LivreDeleteServlet extends HttpServlet {	
	
	/*!
	 * Cette méthode redirige sur la JSP livre_delete permettant de supprimer le livre observé
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService livreService = LivreServiceImpl.getInstance();
		
		String inputId = request.getParameter("id");
		int id = -1;
		
		Livre livre;
		
		try{
			id = String.valueOf(inputId);
			livre = livreService.getById(id);
			
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			livre = new Livre();
			
		} catch(NumberFormatException ebis){
			livre = new Livre();		//Je ne suis pas sûr que cela soit utile, mais dans le doute, ça coûte pas tant que ça d'initialiser "livre"
			throw new ServletException("Erreur lors du parsing : id="+inputId,ebis.getMessage());
		}
		
		request.setAttribute("livre", livre);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_delete.jsp");
		dispatcher.forward(request, response);
	}
	
	/*!
	 * Cette méthode redirige sur la JSP livre_list permettant d'observer la liste des livres
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService livreService = LivreServiceImpl.getInstance();
		
		String inputId = request.getParameter("id");
		int id = -1;
		
		Livre livre;
		
		try{
			id = String.valueOf(inputId);
			livre = livreService.getById(id);
			livreService.delete(livre);
			
		} catch(ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			livre = new Livre();
			
		} catch(NumberFormatException ebis){
			livre = new Livre();		//Je ne suis pas sûr que cela soit utile, mais dans le doute, ça coûte pas tant que ça d'initialiser "livre"
			throw new ServletException("Erreur lors du parsing : id="+inputId,ebis.getMessage());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/livre_list.jsp");
		dispatcher.forward(request, response);
	}
}
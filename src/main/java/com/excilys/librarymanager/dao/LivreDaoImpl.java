package com.excilys.librarymanager.dao;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Livre;

public class LivreDaoImpl implements LivreDao {

    private static LivreDaoImpl instance;
    private LivreDaoImpl() {}
    
    public static LivreDaoImpl getInstance() {
        if(instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    public List<Livre> getList() throws DaoException {

    }

	public Livre getById(int id) throws DaoException {

    }

	public int create(String titre, String auteur, String isbn) throws DaoException {

    }

	public void update(Livre livre) throws DaoException {

    }

	public void delete(int id) throws DaoException {

    }

	public int count() throws DaoException {

    }
}
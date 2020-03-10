package com.excilys.librarymanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.model.Livre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class LivreDaoImpl implements LivreDao {

    private static LivreDaoImpl instance;
    private LivreDaoImpl() {}
    
    public static LivreDaoImpl getInstance() {
        if(instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws DaoException {
        List<Livre> livres = new ArrayList<>();
        String Query = "SELECT id, titre, auteur, isbn FROM livre";
        
		try {
            Connection connection = ConnectionManager.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet res = preparedStatement.executeQuery();
            
			while(res.next()) {
				Livre f = new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
				livres.add(f);
			}
            System.out.println("GET: " + livres);

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
		}
		return livres;
    }

	public Livre getById(int id) throws DaoException {
        Livre livre = new Livre();
        String Query = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?";
        
		try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            
			if(res.next()) {
                livre.setId(id);
                livre.setTitre(res.getString("titre"));
                livre.setAuteur(res.getString("auteur"));
                livre.setIsbn(res.getString("isbn"));
			}
            System.out.println("GET: " + livre);

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
		}
		return livre;
    }

	public int create(String titre, String auteur, String isbn) throws DaoException {
        String Query = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?)";
        int id = 0;
        
		try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();
            if(res.next()) {
                id = res.getInt(1);
            }
            
            System.out.println("CREATE: " + String.valueOf(id));

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
        }
        return id;
    }

	public void update(Livre livre) throws DaoException {

        String Query = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?";
        
		try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getId());
            ResultSet res = preparedStatement.executeQuery();
            
            System.out.println("UPDATE: " + livre);

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
		}
    }

	public void delete(int id) throws DaoException {
        String Query = "DELETE FROM livre WHERE id = ?";
        
		try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();
            
            System.out.println("DELETE: " + String.valueOf(id));

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
		}
    }

	public int count() throws DaoException {
        String Query = "SELECT COUNT(id) AS count FROM livre";
        int count = -1;
        
		try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet res = preparedStatement.executeQuery();
            
            if(res.next()) {
                count = res.getInt("count");
            }

            System.out.println("COUNT: " + String.valueOf(count));

            connection.close();
            preparedStatement.close();
            res.close();
            
		} catch (SQLException e) {
			throw new DaoException("Probleme lors de la recuperation de la liste des films", e);
        }
        return count;
    }
}
package model;

public class Membre{
	/*!
	 *	CHAMPS DE LA TABLE MEMBRE
	 */
	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String telephone;
	private Abonnement	abonnement;
	
	/*!
	 *	CONSTRUCTEUR
	 */
	public Membre(){
		id = 0;
		nom = "Jiang";
		prenom = "Luc";
		adresse = "ENSTA";
		telephone = "118218";
		abonnement = Abonnement.VIP;
	}
	
	/*!
	 *	ACCESSEURS
	 */
	
	/*!
	 *	Retourne l'id du membre.
	 */
	public int getId(){
		return id;
	}
	
	/*!
	 *	Retourne le nom du membre.
	 */
	public String getNom(){
		return nom;
	}
	
	/*!
	 *	Retourne le prénom du membre.
	 */
	public String getPrenom(){
		return prenom;
	}
	
	/*!
	 *	Retourne l'adresse du membre.
	 */
	public String getAdresse(){
		return adresse;
	}
	
	/*!
	 *	Retourne l'email du membre.
	 */
	public String getEmail(){
		return email;
	}
	
	/*!
	 *	Retourne le numéro de téléphone du membre.
	 */
	public String getTelephone(){
		return telephone;
	}
	
	/*!
	 *	Retourne le type d'abonnement du membre.
	 */
	public Abonnement getAbonnement(){
		return abonnement;
	}
	
	
	
	
}
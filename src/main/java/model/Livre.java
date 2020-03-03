package main.java.model;

public class Livre{
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre() {
        this.id = 0;
        this.titre = "Bible";
        this.auteur = "Beaucoup de gens";
        this.isbn = "Je suis l'ISBN de la Bible";
    }
    
    public Livre(int id, String titre, String auteur, String isbn) {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }

    public int getId() {return id;}
    public String getTitre() {return titre;}
    public String getAuteur() {return auteur;}
    public String getIsbn() {return isbn;}

    public void setId(int id) {this.id = id;}
    public void setTitre(String titre) {this.titre = titre;}
    public void setAuteur(String auteur) {this.auteur = auteur;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public String toString() {
        String res = "Id : " + String.valueOf(id) + ", Titre : " + titre + ", Auteur : " + auteur + ", ISBN : " + isbn;
        return res;
    }
}
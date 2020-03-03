package test;

import main.java.model.Livre;
import main.java.model.Membre;
//import main.java.model.Emprunt;

public class ModeleTest {

    public static void main() {
        testLivre();
		testMembre();
		//testEmprunt();
    }
	
	public void testLivre(){
		Livre livre = new Livre();
		System.out.println(livre);
	}
	
	public void testMembre(){
		Membre membre = new Membre();
		System.out.println(membre);
	}
	
	/*public void testEmprunt(){
		Emprunt emprunt = new Emprunt();
		System.out.println(emprunt);
	}*/
}
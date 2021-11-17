package fr.utt.lo02.project;
import java.util.*;

public class JeuCartes {
	
	// attributs d'un jeu de cartes
	private LinkedList<CarteRumeur> tasCartes;
	public static final int nbrCartes = 12; 
	
	// constructeur du jeu de cartes
	public JeuCartes(){
		tasCartes = new LinkedList<CarteRumeur>();
		Witch[] w=Witch.values(); // values renvoie un tableau de Valeur
		Hunt[] h=Hunt.values();
		for(int i=0 ; i < nbrCartes; i++){
			CarteRumeur carte = new CarteRumeur(h[i],w[i]);
			tasCartes.add(carte);	
		}
	}
	
	// retire la premi�re carte du tas de cartes
	public CarteRumeur distribuerUneCarte(int i){ 
		return this.tasCartes.get(i);
	}
	
	// M�lange de toutes les cartes. Appel de la m�thode statique shuffle de la classe Collections
	public void melanger(){
		Collections.shuffle(tasCartes);
	}
	
	
	// le tas de cartes est-il vide?
	public boolean estVide() {
		int tailleTas = this.tasCartes.size();
		if(tailleTas == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString(){
		return tasCartes.toString();
	}
	
	// getter du tas de cartes
	public LinkedList<CarteRumeur> getTasCartes(){
		return tasCartes;
	}
}
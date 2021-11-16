package fr.utt.lo02.project;

import java.util.*;

public class Joueur {
    private int numJoueur;
    private boolean accused;
    private String role;
    private boolean victoire;
    
    public Joueur(int numJoueur) {
    	this.numJoueur = numJoueur;
    	this.victoire = false;
    	this.elimine = false;
    	this.accused = false;
    	this.point = 0;
    	this.role = null;
    	
    }

    public boolean isVictoire() {
        // Automatically generated method. Please do not modify this code.
        return this.victoire;
    }

    public void setVictoire(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.victoire = value;
    }

    private boolean elimine;

    public boolean isElimine() {
        // Automatically generated method. Please do not modify this code.
        return this.elimine;
    }

    public void setElimine(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.elimine = value;
    }
    
    public void setRole(String role) {
    	this.role = role;
    }
    public String getRole() {
    	return this.role;
    }
    public int getNumJoueur() {
    	return this.numJoueur;
    }

    private int point;

    public int getPoint() {
        // Automatically generated method. Please do not modify this code.
        return this.point;
    }

    public void setPoint(int value) {
        // Automatically generated method. Please do not modify this code.
        this.point = value;
    }


    public boolean isAccused() {
        // Automatically generated method. Please do not modify this code.
        return this.accused;
    }

    public void setAccused(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accused = value;
    }

    public CarteID carteID;

    public ArrayList<CarteRumeur> carteRumeur = new ArrayList<CarteRumeur> ();


    public void jouer() {
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ******************************************* \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " possède ces cartes :\n");
		sb.append(main);
		sb.append("\n ******************************************* \n");
		return sb.toString();
	}

}

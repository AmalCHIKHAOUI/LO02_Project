package fr.utt.lo02.project;

import java.util.*;

import td8_lo02_collection.Carte;

public class Joueur {
    private int numJoueur;
    private boolean accused;
    private String role;
    private boolean victoire;
    public int point;
    public LinkedList<CarteRumeur> main = new LinkedList<CarteRumeur> ();

    
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


    public int getPoint() {
        // Automatically generated method. Please do not modify this code.
        return this.point;
    }

    public void setPoint(int value) {
        // Automatically generated method. Please do not modify this code.
        this.point = value;
    }
    
    public void ramasserCarte(CarteRumeur carte){
		this.main.add(carte);
	}


    public void isAccused(Scanner scanner) {
    	System.out.print("Voulez-vous révéler votre rôle où révéler l'effet Witch d'une carte rumeur ? Entrez 'role' ou 'witch' : ");
		if(scanner.nextLine()=="role") {
			System.out.println("Le rôle du joueur "+ this.numJoueur +" est " + this.role);
			if(this.role=="witch") {
				this.setElimine(true);
			}
				
		}
		else if(scanner.nextLine()=="witch") {
			this.main.get(0).toString();
		}
    }

    public void setAccused(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.accused = value;
    }

    public CarteID carteID;



    public void jouer(Partie partie, Scanner scanner) {
   
    	System.out.print("accuser ou révéler une carte rumeur ? (entrer 'accuser' ou 'reveler') ");
		String action = scanner.nextLine();
		
		if(action == "accuser") {
			System.out.print("Le numéro du joueur que vous accusez : ");
    		int numJoueurAcc = scanner.nextInt();
    		Joueur joueurAccused = partie.joueur.get(numJoueurAcc);
    		joueurAccused.isAccused(scanner);
    		
		}
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ---------------------------------------- \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " possède ces cartes :\n");
		sb.append(main);
		sb.append("\n ---------------------------------------- \n");
		return sb.toString();
	}

}

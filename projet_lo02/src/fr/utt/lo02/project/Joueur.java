package fr.utt.lo02.project;

import java.util.*;

public class Joueur {
    protected int numJoueur;
    protected boolean accused;
    protected String role;
    protected boolean victoire;
    protected int point;
    protected boolean elimine;
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
    
    public void revelerCarteRumeur(CarteRumeur carte) {
    	CarteRumeur carte = this.main.get(numCarte-1);
    	System.out.println(carte);
    }


    public void isAccused(Scanner scanner) {
    	System.out.print("Voulez-vous r�v�ler votre r�le o� r�v�ler l'effet Witch d'une carte rumeur ? Entrez 'role' ou 'witch' : ");
		if(scanner.nextLine()=="role") {
			System.out.println("Le r�le du joueur "+ this.numJoueur +" est " + this.role);
			if(this.role=="witch") {
				this.elimine = true;
			}
				
		}
		else if(scanner.nextLine()=="witch") {
			this.main.get(0).toString();
		}
    }



    public void jouer(Partie partie, Scanner scanner) {
   
    	System.out.print("accuser ou r�v�ler une carte rumeur ? (entrer 'accuser' ou 'reveler') ");
		String action = scanner.nextLine();
		
		if(action == "accuser") {
			System.out.print("Le num�ro du joueur que vous accusez : ");
    		int numJoueurAcc = scanner.nextInt();
    		Joueur joueurAccused = partie.joueur.get(numJoueurAcc-1);
    		joueurAccused.isAccused(scanner);
    		if(joueurAccused.elimine == true) {
    			this.point++;
    			partie.turn = this.numJoueur; // le joueur actuel prend la main
    		}
    		else {
    			partie.turn = numJoueurAcc; // le joueur accus� prend la main
    		}
		}
		else if(action == "reveler") {
			System.out.print("Le num�ro de la carte que vous voulez utiliser  : ");
    		int numCarteRevelee = scanner.nextInt();
    		CarteRumeur carteJouee = this.main.get(numCarteRevelee-1);
    		this.revelerCarteRumeur(carteJouee);
    		
    		carteJouee.effetHunt(); // activer effet hunt!
    		
		}
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ---------------------------------------- \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " poss�de ces cartes : \n");
		sb.append(this.main);
		sb.append("\n ---------------------------------------- \n");
		return sb.toString();
	}
    /*public static void main (String[] args) {
    	Joueur j=new Joueur(3);
    	CarteRumeur c1 = new CarteRumeur(Hunt.AngryMob,Witch.AngryMob);
    	CarteRumeur c2 = new CarteRumeur(Hunt.Inquisition,Witch.Inquisition);
    	j.main.add(c1);
    	j.main.add(c2);
    	System.out.println(j);


    	
    }*/

}

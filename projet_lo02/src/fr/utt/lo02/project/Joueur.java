package fr.utt.lo02.project;

import java.util.*;

public class Joueur {
    protected int numJoueur;
    protected boolean accused;
    protected String role;
    protected boolean victoire;
    protected int point;
    protected boolean elimine;
    public LinkedList<CarteRumeur> main = new LinkedList<CarteRumeur>();
    public LinkedList<CarteRumeur> carteRevelee = new LinkedList<CarteRumeur>();

    public static Scanner scanner = new Scanner(System.in);
    
    private final static int pointMax = 5;



    
    public Joueur(int numJoueur) {
    	this.numJoueur = numJoueur;
    	this.victoire = false;
    	this.elimine = false;
    	this.accused = false;
    	this.point = 0;
    	this.role = null;
    	
    }

    public boolean isVictoire() {
    	if(this.point >= pointMax) {
    		return this.victoire = true;
    	}
    	else {
            return this.victoire = false;
    	}
    }

    public void setVictoire(boolean value) {
        // Automatically generated method. Please do not modify this code.
        this.victoire = value;
    }

    public void melanger(){
		Collections.shuffle(this.main);
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

    
    public void ramasserCarte(CarteRumeur carte){
		this.main.add(carte);
	}
    public void retirerCarte(CarteRumeur carte, Partie partie) {
    	this.main.remove(carte);
    	this.carteRevelee.add(carte);
    }
    
    public void revelerCarteRumeur(CarteRumeur carte) {
    	System.out.println(carte);
    }


    public Partie isAccused(Partie partie, Joueur joueurAccusant) {
    	System.out.print("Voulez-vous révéler votre rôle où révéler l'effet Witch d'une carte rumeur ? Entrez 'role' ou 'witch' : ");
		if(scanner.nextLine()=="role") {
			System.out.println("Le rôle du joueur "+ this.numJoueur +" est " + this.role);
			if(this.role=="witch") {
				this.elimine = true;
			}
				
		}
		else if(scanner.nextLine()=="witch") {
			System.out.println("Voici la carte que vous avez révélé avec son effet Witch? : ");
			System.out.println("-------------------------------------------------------------");
			System.out.println(this.main.get(0));
			partie.effetWitch(this.main.get(0), joueurAccusant.numJoueur, this.numJoueur);
		}
		return partie;
    }



    public Partie jouer(Partie partie) {
   
    	System.out.print("accuser ou révéler une carte rumeur ? (entrer 'accuser' ou 'reveler') ");
		String action = scanner.nextLine();
		
		if(action == "accuser") {
			System.out.print("Le numéro du joueur que vous accusez : ");
    		int numJoueurAcc = scanner.nextInt();
    		Joueur joueurAccused = partie.joueur.get(numJoueurAcc-1);
    		partie = joueurAccused.isAccused(partie,this);
    		if(joueurAccused.elimine == true) {
    			this.point++;
    			partie.turn = this.numJoueur; // le joueur actuel prend la main
    		}
    		else {
    			partie.turn = numJoueurAcc; // le joueur accusé prend la main
    		}
		}
		else if(action == "reveler") {
			System.out.print("Le numéro de la carte que vous voulez utiliser  : ");
    		int numCarteRevelee = scanner.nextInt();
    		CarteRumeur carteJouee = this.main.get(numCarteRevelee-1);
    		this.revelerCarteRumeur(carteJouee);
    		
    		partie.effetHunt(); // activer effet hunt!
    		
		}
		return partie;
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ---------------------------------------- \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " possède ces cartes : \n");
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

package fr.utt.lo02.project;

import java.util.*;

public class Joueur {
    protected int numJoueur;
    protected boolean accused;
    protected String role;
    protected boolean victoire;
    protected int point;
    protected boolean elimine;
    protected boolean revealed;
    public LinkedList<CarteRumeur> main = new LinkedList<CarteRumeur>();
    public LinkedList<CarteRumeur> carteRevelee = new LinkedList<CarteRumeur>();
    
    // constante utilisée pour savoir si un joueur est witch ou villager
    protected final String w="witch"; 
    protected final String v="villager";
    
    private static boolean check = false; // à utiliser pour vérifier des conditions d'entrée pour l'utilisateur


    public static Scanner scanner = new Scanner(System.in);
    
    private final static int pointMax = 5;



    
    public Joueur(int numJoueur) {
    	this.numJoueur = numJoueur;
    	this.victoire = false;
    	this.elimine = false;
    	this.accused = false;
    	this.point = 0;
    	this.role = null;
    	this.revealed = false;
    	
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
    public boolean estVide(LinkedList<CarteRumeur> carte) {
		int tailleTas = carte.size();
		if(tailleTas == 0) {
			return true;
		}
		else {
			return false;
		}
	}


    public Partie isAccused(Partie partie, Joueur joueurAccusant) {
    	int action = 0;
    	check=false;
    	while(check == false) {
    		System.out.println("-------------------------------------------------------------");
        	System.out.print("Joueur "+this.numJoueur+" - Voulez-vous révéler votre rôle(1) ou révéler l'effet Witch d'une carte rumeur(2) ? ");
        	action = scanner.nextInt();
        	
    		if(action == 1 || (action == 2 && this.estVide(main)==false)) {
    			check = true;
    		}
    		else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
    	}
		if(action==1) {
			this.revealed = true;
			System.out.println("-------------------------------------------------------------");
			System.out.println("Le rôle du joueur "+ this.numJoueur +" est " + this.role);
			System.out.println("-------------------------------------------------------------");
			if(this.role.equalsIgnoreCase(this.w)) {
				this.elimine = true;
			}
				
		}
		else {
			System.out.println("-------------------------------------------------------------");
			System.out.println("Joueur "+this.numJoueur+" - Voici votre jeu : ");
			System.out.println(this.main);
			check = false;
			int numCarteRevelee = 0;
			while(check == false) {
				System.out.println("-------------------------------------------------------------");
				System.out.print("Entrez le numéro de la carte que vous voulez révéler : ");
				numCarteRevelee = scanner.nextInt();
	        	
	    		if(numCarteRevelee > 0 && numCarteRevelee <= this.main.size()) {
	    			check = true;
	    		}
	    		else {
	        		System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	        	}
	    	}
			System.out.println("-------------------------------------------------------------");
			System.out.println("Voici la carte que vous avez révélé avec son effet Witch? : ");
			System.out.println("-------------------------------------------------------------");
    		this.revelerCarteRumeur(this.main.get(numCarteRevelee-1));
			partie.effetWitch(this.main.get(numCarteRevelee-1), joueurAccusant.numJoueur, this.numJoueur);
			
			this.carteRevelee.add(this.main.get(numCarteRevelee-1)); // supprimer la carte jouée après l'avoir activé
    		this.main.remove(numCarteRevelee-1);
		}
		return partie;
    }



    public Partie jouer(Partie partie, int nbJoueurTot) {
    	
    	
    	int action = 0;
    	check = false;
    	
    	while(check == false) {
    		System.out.println("---------------------------------");
        	System.out.print("accuser(1) ou révéler l'effet Witch? d'une carte rumeur (2) ? ");
    		action = scanner.nextInt();
    		if(action == 1 || (action == 2 && this.estVide(main)==false)) {
    			check = true;
    		}
    		else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
    	}
    	
		/*String accuser = "accuser";
		String reveler = "reveler";*/
    	
    	int numJoueurAcc = 0;
    	Joueur joueurAccused = null;
    	
		if(action == 1) {
			
	    	check = false;
	    	while(check == false) {
	    		System.out.println("---------------------------------");
				System.out.print("Entrez le numéro du joueur que vous voulez accuser : ");
	    		numJoueurAcc = scanner.nextInt();
	    		joueurAccused = partie.joueur.get(numJoueurAcc-1);
	    		if(numJoueurAcc > 0 && numJoueurAcc <= nbJoueurTot && numJoueurAcc != this.numJoueur && this.revealed == false) {
	    			check = true;
	    		}
	    		else {
	    			System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	    		}
	    	}

	    	
    		partie = joueurAccused.isAccused(partie,this);
    		if(joueurAccused.elimine == true) {
    			this.point++;
    			partie.turn = this.numJoueur; // le joueur actuel prend la main
    		}
    		else {
    			partie.turn = numJoueurAcc; // le joueur accusé prend la main
    		}
		}
		else if(action == 2) {
			int numCarteRevelee = 0;
    		check = false;
	    	while(check == false) {
	    		System.out.println("---------------------------------");
				System.out.print("Entrez le numéro de la carte que vous voulez utiliser  : ");
	    		numCarteRevelee = scanner.nextInt();
	    		if(numCarteRevelee > 0 && numCarteRevelee <= this.main.size()) {
	    			check = true;
	    		}
	    		else {
	    			System.out.println("---------------------------------");
	            	System.out.println("La saisie est incorrecte");
	            	System.out.println("---------------------------------");
	    		}
	    	}
    		
    		CarteRumeur carteJouee = this.main.get(numCarteRevelee-1);
    		this.revelerCarteRumeur(carteJouee);
    		
    		partie.effetHunt(carteJouee,this.numJoueur); // activer effet hunt!
    		
    		this.carteRevelee.add(carteJouee); // supprimer la carte jouée après l'avoir activé
    		this.main.remove(numCarteRevelee-1);
    		
		}
		return partie;
    }
    public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("\n ---------------------------------------- \n");		
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " et possède ces cartes : \n");
		sb.append(this.main);
		sb.append("\n ---------------------------------------- \n");
		return sb.toString();
	}
    /*public static void main (String[] args) {
    	Joueur j=new Joueur(3);
    	CarteRumeur c1 = new CarteRumeur(Hunt.ANGRYMOB,Witch.ANGRYMOB);
    	CarteRumeur c2 = new CarteRumeur(Hunt.INQUISITION,Witch.INQUISITION);
    	j.main.add(c1);
    	j.main.add(c2);
    	System.out.println(j.main);


    	
    }*/

}

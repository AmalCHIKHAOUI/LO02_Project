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
    	
		System.out.println("-------------------------------------------------------------");
    	System.out.print("Joueur "+this.numJoueur+" - Voulez-vous révéler votre rôle(1) ou révéler l'effet Witch d'une carte rumeur(2) ? ");
		if(scanner.nextInt()==1) {
			this.revealed = true;
			System.out.println("-------------------------------------------------------------");
			System.out.println("Le rôle du joueur "+ this.numJoueur +" est " + this.role);
			System.out.println("-------------------------------------------------------------");
			if(this.role.equalsIgnoreCase(this.w)) {
				this.elimine = true;
			}
				
		}
		else if(scanner.nextInt()==2) {
			System.out.println("-------------------------------------------------------------");
			System.out.print("Entrez le numéro de la carte que vous voulez révéler : ");
			int numCarteRevelee = scanner.nextInt();
			System.out.println("-------------------------------------------------------------");
			System.out.println("Voici la carte que vous avez révélé avec son effet Witch? : ");
			System.out.println("-------------------------------------------------------------");
    		this.revelerCarteRumeur(this.main.get(0));
			partie.effetWitch(this.main.get(0), joueurAccusant.numJoueur, this.numJoueur);
			
			this.carteRevelee.add(this.main.get(numCarteRevelee-1)); // supprimer la carte jouée après l'avoir activé
    		this.main.remove(numCarteRevelee-1);
		}
		return partie;
    }



    public Partie jouer(Partie partie) {
    	
    	System.out.println("---------------------------------");
    	System.out.print("accuser(1) ou révéler(2) une carte rumeur ? ");
		int action = scanner.nextInt();
		/*String accuser = "accuser";
		String reveler = "reveler";*/
		if(action == 1) {
		//if(action.equalsIgnoreCase(accuser)) {
	    	System.out.println("---------------------------------");
			System.out.print("Entrez le numéro du joueur que vous voulez accuser : ");
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
		else if(action == 2) {
	    	System.out.println("---------------------------------");
			System.out.print("Entrez le numéro de la carte que vous voulez utiliser  : ");
    		int numCarteRevelee = scanner.nextInt();
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
		sb.append("Le joueur " +  this.numJoueur + " est un " + this.role + " possède ces cartes : \n");
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

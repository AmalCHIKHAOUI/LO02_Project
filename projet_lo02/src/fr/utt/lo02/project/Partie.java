package fr.utt.lo02.project;

import java.util.*;

import td8_lo02_collection.Joueur;

public class Partie {
    private Partie objetUnique;
    private int round;
    private int turn;
    public ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    private final static int pointMax = 5;


    
    public Partie(int nbJoueur, int nbOrdi) {
    	this.nbJoueur = nbJoueur;
    	this.nbOrdi = nbOrdi;
    	this.round = 1;

    }
    private Partie getObjetUnique() {
        // Automatically generated method. Please do not modify this code.
        return this.objetUnique;
    }

    private void setObjetUnique(Partie value) {
        // Automatically generated method. Please do not modify this code.
        this.objetUnique = value;
    }


    
    public void initListeJoueur(int nbJoueur, int nbOrdi) {
    	for (int i = 1; i <= nbJoueur+nbOrdi; i++) {
    		if (i<=nbJoueur) {
    			this.joueur.add(new Joueur(i));
    		}
    		else {
    			this.joueur.add((Ordi)new Joueur(i));
    		}
    		
    	}	
    }

    private int nbOrdi;

    private int getNbOrdi() {
        // Automatically generated method. Please do not modify this code.
        return this.nbOrdi;
    }

    private void setNbOrdi(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbOrdi = value;
    }

    private int nbJoueur;

    private int getNbJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.nbJoueur;
    }

    private void setNbJoueur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbJoueur = value;
    }

    private int gagnant;

    private int getGagnant() {
        // Automatically generated method. Please do not modify this code.
        return this.gagnant;
    }

    private void setGagnant(int value) {
        // Automatically generated method. Please do not modify this code.
        this.gagnant = value;
    }

    //public ArrayList<Round> round = new ArrayList<Round> ();

    public int getRound() {
    	return this.round;
    }
    public void setRound(int round) {
    	this.round = round;
    }
    public void setTurn(int turn) {
    	this.turn = turn;
    }

    public void getInstance() {
    }

    public boolean gagne() {
    	boolean gagne = false;
		Iterator<Joueur> it =joueur.iterator();
		while (it.hasNext() && gagne == false) {
			Joueur j = it.next();
			gagne = j.isVictoire();
		}
		return gagne;
    }

    public void debutJeu() {
    	System.out.println("--------------------------");
    	System.out.println("La partie commence !!!!!!");
    	System.out.println("--------------------------");

    }

    public void finJeu(int numJoueur) {
    	System.out.println("--------------------------");
    	System.out.println("Le joueur " + numJoueur + " a gagné");
    	System.out.println("--------------------------");
    	System.out.println("Le jeu est terminé !!!!!!");
    	System.out.println("--------------------------");
    }
    
    public static void main(String[] args) {
    	System.out.println("---------------------------------");
    	System.out.println("Bienvenue dans le Jeu Witch Hunt");
    	System.out.println("---------------------------------");
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.print("Combien de joueurs physiques : ");
    	int nbJoueur = scanner.nextInt();
    	System.out.print("Combien de joueurs virtuels : ");
    	int nbOrdi = scanner.nextInt();
    	Partie partie = new Partie(nbJoueur, nbOrdi);
    	partie.debutJeu();
    	
    	System.out.print("Qui commence ? "); // Choisir qui commence au début du jeu
    	int turn = scanner.nextInt();
    	partie.setTurn(turn);
    	Joueur joueurActuel = partie.joueur.get(turn-1);

    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	while (partie.gagne() == false) {
    		joueurActuel = partie.joueur.get(turn-1);
    		partie.turn++;
    		
    	}
    	int numGagnant = joueurActuel.getNumJoueur();
    	partie.finJeu(numGagnant);
    	
     	
    }

}

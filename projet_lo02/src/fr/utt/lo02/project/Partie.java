package fr.utt.lo02.project;

import java.util.*;


public class Partie {
    protected Partie objetUnique;
    protected int round;
    protected int turn;
    protected int nbJoueur;
    protected int nbOrdi;
    protected int gagnant;



    public ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    public static Scanner scanner = new Scanner(System.in);
	private JeuCartes jeu;

    
    private final static int pointMax = 5;


    
    public Partie(int nbJoueur, int nbOrdi, JeuCartes jeu) {
    	this.nbJoueur = nbJoueur;
    	this.nbOrdi = nbOrdi;
    	this.round = 1;
    	this.turn = 1;
    	this.gagnant = 0;
    	this.jeu = jeu;

    }
    public Partie getObjetUnique() {
        // Automatically generated method. Please do not modify this code.
        return this.objetUnique;
    }

    public void setObjetUnique(Partie value) {
        // Automatically generated method. Please do not modify this code.
        this.objetUnique = value;
    }


    
    public void initListeJoueur(int nbJoueur, int nbOrdi) {
    	for (int i = 1; i <= nbJoueur+nbOrdi; i++) {
    		if (i<=nbJoueur) {
    			this.joueur.add(new Joueur(i));
    		}
    		else {
    			this.joueur.add((Bot)new Joueur(i));
    		}
    		
    	}	
    }
    
    


    public int getNbOrdi() {
        // Automatically generated method. Please do not modify this code.
        return this.nbOrdi;
    }

    public void setNbOrdi(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbOrdi = value;
    }


    public int getNbJoueur() {
        // Automatically generated method. Please do not modify this code.
        return this.nbJoueur;
    }

    public void setNbJoueur(int value) {
        // Automatically generated method. Please do not modify this code.
        this.nbJoueur = value;
    }


    public int getGagnant() {
        // Automatically generated method. Please do not modify this code.
        return this.gagnant;
    }

    public void setGagnant(int value) {
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
		return gagne ;
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
    public void debutRound(int nbJoueur) { // on distribue les cartes à tout le monde
    	System.out.println("--------------------------");
    	System.out.println("Le round commence !!!!!!");
    	System.out.println("--------------------------");
    	this.jeu.melanger(); // mélanger les cartes
    	int tempNbCarte = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
			Joueur j = (Joueur)it.next();
			
			if(nbJoueur == 3) {
				
				for(int i=tempNbCarte;i<tempNbCarte+4;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 4;
			}
			if(nbJoueur == 4) {
				for(int i=tempNbCarte;i<tempNbCarte+3;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 3;
			}
			if(nbJoueur == 5) {
				for(int i=tempNbCarte;i<tempNbCarte+2;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 2;
			}
			if(nbJoueur == 6) {
				for(int i=tempNbCarte;i<tempNbCarte+2;i++) {
					j.ramasserCarte(this.jeu.distribuerUneCarte(i));
				}
				tempNbCarte += 2;
			}
		}
    	
    }
    
    public static void main(String[] args) {
    	System.out.println("---------------------------------");
    	System.out.println("Bienvenue dans le Jeu Witch Hunt");
    	System.out.println("---------------------------------");
    	
    	JeuCartes jeu = new JeuCartes();
    	System.out.print("Combien de joueurs physiques : ");
    	int nbJoueur = scanner.nextInt();
    	System.out.print("Combien de joueurs virtuels : ");
    	int nbOrdi = scanner.nextInt();
    	Partie partie = new Partie(nbJoueur, nbOrdi, jeu);
    	
    	int nbJoueurTot = nbJoueur + nbOrdi ;
    	
    	partie.debutJeu();
    	
    	System.out.print("Qui commence ? "); // Choisir qui commence au début du jeu
    	partie.turn=scanner.nextInt();
    	
    	Joueur joueurActuel = partie.joueur.get(partie.turn -1);

    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	while (partie.gagne() == false) {
    		
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			System.out.print("Joueur n° "+j.getNumJoueur()+" - Choisir votre rôle : witch ou villager ?");
        		String role = scanner.nextLine();
        		j.setRole(role);
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		joueurActuel = partie.joueur.get(partie.turn -1);
    		
    		System.out.println(joueurActuel); //afficher le numéro et les cartes du Joueur
    		
    		joueurActuel.jouer(partie, scanner); // le joueur qui a la main joue
    		
    		
    		partie.turn++;
    		if(partie.turn>nbJoueurTot) { // retourner au joueur 1 après le tour du dernier joueur
    			partie.turn = 1;
    		}
    		
    	}
    	
    	partie.gagnant = joueurActuel.getNumJoueur();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }

}

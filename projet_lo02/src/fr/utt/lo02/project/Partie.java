package fr.utt.lo02.project;

import java.util.*;


public class Partie {
    protected Partie objetUnique;
    protected int round;
    protected int turn;
    protected int nbJoueur;
    protected int nbOrdi;
    protected int gagnant;
    protected int nbJoueurElimine;



    public ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    public static Scanner scanner = new Scanner(System.in);
	protected JeuCartes jeu;

    


    
    public Partie(int nbJoueur, int nbOrdi, JeuCartes jeu) {
    	this.nbJoueur = nbJoueur;
    	this.nbOrdi = nbOrdi;
    	this.round = 1;
    	this.turn = 1;
    	this.gagnant = 0;
    	this.jeu = jeu;
    	this.nbJoueurElimine = 0;

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
    	    	
    			System.out.println("Tapez 1 pour prudent et tapez 2 pour agressive");
    			int strateg=scanner.nextInt();
    			Bot nouveauBot = (Bot) new Joueur(i);
    			nouveauBot.setstrateg(strateg);
    			this.joueur.add(nouveauBot);
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
    
    public void countJoueurElimine() {
    	this.nbJoueurElimine = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
			Joueur j = (Joueur)it.next();
			if (j.elimine == true) {
				this.nbJoueurElimine++;
			}
			
		}
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
    public void debutRound(int nbJoueur) { // on distribue les cartes à tout les joueurs
    	System.out.println("--------------------------");
    	System.out.println("Le round commence !!!!!!");
    	System.out.println("--------------------------");
    	this.nbJoueurElimine = 0; // on reset le nb de joueurs éliminés à 0
    	this.jeu.melanger(); // mélanger les cartes
    	int tempNbCarte = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
			Joueur j = (Joueur)it.next();
			
			j.carteRevelee.clear(); // supprimer toutes les cartes faussées du joueur sélectionné
			
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
    
    public void effetWitch(CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
    	if(carte.witch == Witch.ANGRYMOB && carte.witch == Witch.BROOMSTICK && carte.witch == Witch.WART && carte.witch == Witch.TOAD && carte.witch == Witch.BLACKCAT && carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		this.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){ // reprendre carte révélée
    		Joueur j = this.joueur.get(numJoueurAccuse-1);
    		j.main.add(j.carteRevelee.get(0));
    		j.carteRevelee.remove(0); // on enlève la carte des cartes révélées
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // prendre une carte de l'adversaire
    		Joueur j = this.joueur.get(numJoueurAccuse-1); // joueur accusé
    		Joueur j1 = this.joueur.get(numJoueurAccusant-1); // joueur accusant
    		j.main.add(j1.main.get(0));
    		j1.main.remove(0); // on enlève la carte de la main du joueur auccanst
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL && carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		Joueur j1 = this.joueur.get(numJoueurAccusant-1);
    		j1.melanger();
    		j1.carteRevelee.add(j1.main.get(0));
    		j1.main.remove(0);
    		this.turn = numJoueurAccuse;
    		
    	}
    	
    }

    public void effetHunt() {
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
    	
    	// ----------- boucle de la partie ---------------
    	while (partie.gagne() == false) { 
    		
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			System.out.print("Joueur n° "+j.getNumJoueur()+" - Choisir votre rôle : witch ou villager ?");
        		String role = scanner.nextLine();
        		j.setRole(role);
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurElimine < nbJoueurTot-1) { 
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
        		System.out.println(joueurActuel); //afficher le numéro et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie); // le joueur qui a la main joue
        		
        		partie.countJoueurElimine();
        		
        		/*partie.turn++;
        		
        		if(partie.turn>nbJoueurTot) { // retourner au joueur 1 après le tour du dernier joueur
        			partie.turn = 1;
        		}
        		
        		partie.round++;*/
        		
    		}
    		
    		Iterator<Joueur> it1 = partie.joueur.iterator();
    		while(it1.hasNext()) {
    			Joueur joueurRes = it1.next();
    			if(joueurRes.elimine == false) {
    				joueurActuel = joueurRes;
    			}
    		}
    		
    		if(joueurActuel.role == "witch") {
    			joueurActuel.point += 2;
    		}
    		else if(joueurActuel.role =="villager") {
    			joueurActuel.point++;
    		}
    		
    		
    		
    	}
    	
    	partie.gagnant = joueurActuel.getNumJoueur();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }

}

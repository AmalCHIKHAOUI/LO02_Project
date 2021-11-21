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
		Joueur j = this.joueur.get(numJoueurAccuse-1); // joueur accusé
		Joueur j1 = this.joueur.get(numJoueurAccusant-1); // joueur accusant

    	if(carte.witch == Witch.ANGRYMOB || carte.witch == Witch.BROOMSTICK || carte.witch == Witch.WART || carte.witch == Witch.TOAD || carte.witch == Witch.BLACKCAT || carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		this.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){ // reprendre carte révélée
    		j.main.add(j.carteRevelee.get(0));
    		j.carteRevelee.remove(0); // on enlève la carte des cartes révélées
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // prendre une carte de l'adversaire
    		j.main.add(j1.main.get(0));
    		j1.main.remove(0); // on enlève la carte de la main du joueur auccanst
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		j1.melanger();
    		j1.carteRevelee.add(j1.main.get(0));
    		j1.main.remove(0);
    		this.turn = numJoueurAccuse;
    		
    	}
    	
    }

    public void effetHunt(CarteRumeur carte, int numJoueur) {
		Joueur j = this.joueur.get(numJoueur-1);

    	if(carte.hunt == Hunt.ANGRYMOB){ // révéler la carte d'un autre joueur
    		System.out.println("------------------------");
    		System.out.println("Entrer le numéro du joueur dont vous voulez révéler l'identité :");
    		int numJoueurChoisi = scanner.nextInt();
    		System.out.println("------------------------");
    		
    		Joueur j1 = this.joueur.get(numJoueurChoisi-1);
    		System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);
    		if(j1.role=="witch") {
    			j.point+=2;
    			this.turn = numJoueur;
    		}
    		else if(j1.role=="villager"){
    			j.point-=2;
    			if(j.point < 0) { // on vérifie que le score du joueur n'est pas négatif
    				j.point=0;
    			}
    			this.turn = j1.numJoueur; // on donne la main au joueur dont l'identité a été révélé
    		}
    		


    	}
    	else if(carte.hunt==Hunt.INQUISITION) {
			System.out.println("----------------------------------") ;
			System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("Voici l'identité du joueur que vous avez choisi (à regarder discrètement) : "+this.joueur.get(this.turn-1).role) ;
    		System.out.println("----------------------------------") ;

    	}
    	else if(carte.hunt==Hunt.POINTEDHAT) {
    		System.out.println("----------------------------------") ;
			System.out.println("L'effet Hunt de cette carte vous permet donc de reprendre une de vos cartes révélées");
			if(j.estVide(j.carteRevelee)==true) {
				System.out.println("Vous n'avez pas encore révélé de cartes ! ");
			}
			else {
				j.main.add(j.carteRevelee.get(0)); // on insère la carte révélée dans votre main
				j.carteRevelee.remove(0);
			}
			System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("Voici l'identité du joueur que vous avez choisi (à regarder discrètement) : "+this.joueur.get(this.turn-1).role) ;
    		System.out.println("----------------------------------") ;

    	}
    	else if(carte.hunt==Hunt.HOOKEDNOSE) {
    		System.out.println("----------------------------------") ;
    		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous avez la possibilité de lui prendre une carte aléatoire") ;
    		Joueur j1 = this.joueur.get(this.turn-1);
    		if(j1.estVide(j1.main)==false) {
    			j.melanger(); // mélanger les cartes du joueur
    			j.main.add(j1.main.get(0)); // le joueur j prend une carte aléatoire à j1
    			j1.main.remove(0);
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous lui avez pris une carte avec succès !!!!") ;
        		System.out.println("----------------------------------") ;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a plus de carte dans sa main !!") ;
        		System.out.println("----------------------------------") ;
    		}

    	}
    	else if(carte.hunt == Hunt.BROOMSTICK || carte.hunt == Hunt.WART){ //choisir le prochain joueur
    		System.out.println("----------------------------------") ;
    		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;

    		
    	}
    	else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    		System.out.println("----------------------------------") ;
    		System.out.println("Entrez le numéro du joueur que vous voulez choisir : ");
    		int numJ = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		Joueur j1 = this.joueur.get(numJ-1);
    		System.out.println("Joueur n°"+numJ+" : Révéler votre identité ou supprimer une carte ? Entrez 'reveler' ou 'supprimer' ");
    		if(scanner.nextLine()=="reveler") {
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur n°"+numJ+" : vous êtes un "+j1.role);
        		if(j1.role=="witch") {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous gagnez 1 point et vous prenez le prochain tour") ;
            		j.point++;
        			this.turn=numJoueur;
        		}
        		else if(j1.role=="villager") {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous perdez 1 point et il prend le prochain tour") ;
            		j.point--;
        			this.turn=numJ;
        		}

    		}
    		else if(scanner.nextLine()=="supprimer") { // il supprime une carte
        		System.out.println("----------------------------------") ;
        		System.out.println(j1) ;
        		System.out.println("Entrez le numéro de la carte que vous voulez supprimer : ") ;
        		int numCarte = scanner.nextInt();
    			j1.carteRevelee.add(j1.main.get(numCarte-1));
    			j1.main.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Carte supprimée !!!") ;

    		}
    		
    	}
    	else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous devez révéler votre identité : vous êtes un "+j.role);
    		if(j.role=="witch") {
    			this.turn++;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
    		}
    	}
    	else if(carte.hunt == Hunt.EVILEYE) {
    		System.out.println("----------------------------------") ;
    		System.out.println("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    	}
    	else if(carte.hunt == Hunt.BLACKCAT) {
    		if(j.estVide(j.carteRevelee)==false) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Voici les cartes supprimées : ");
        		System.out.println(j.carteRevelee);
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous prenez le prochain tour");
    		this.turn = numJoueur;
    	}
    	else if(carte.hunt == Hunt.PETNEWT) {
    		
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

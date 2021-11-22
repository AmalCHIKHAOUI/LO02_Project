package fr.utt.lo02.project;

import java.util.*;


public class Partie {
    protected Partie objetUnique;
    protected int round;
    protected int turn;
    protected int nbJoueur;
    protected int nbOrdi;
    protected int gagnant;
    
    private int nbJoueurElimine;
    private int nbJoueurRevele;


    // constante utilisée pour savoir si un joueur est witch ou villager
    protected final String w="witch"; 
    protected final String v="villager";

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
    	this.nbJoueurRevele = 0;


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
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non éliminés: ");
    	this.nbJoueurElimine = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.elimine == true) {
				this.nbJoueurElimine++;
			}
			else {
		    	System.out.println("Joueur n°"+j.numJoueur);
			}
			
		}
    	System.out.println("----------------------------------- ");

    }
    public void countJoueurRevele() {
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non révélés : ");
    	this.nbJoueurRevele = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.revealed == true) {
				this.nbJoueurRevele++;
			}
			else {
		    	System.out.println("Joueur n°"+j.numJoueur);
			}
			
		}
    	System.out.println("----------------------------------- ");

    }

    public int gagne() {
    	int gagne = 0;
		Iterator<Joueur> it =joueur.iterator();
		while (it.hasNext() && gagne == 0) {
			Joueur j = it.next();
			if( j.isVictoire() == true) {
				gagne = j.numJoueur;
			}
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
    	
    	this.nbJoueurElimine = 0; // on reset le nb de joueurs éliminés à 0
    	this.nbJoueurRevele = 0;
    	this.jeu.melanger(); // mélanger les cartes
    	int tempNbCarte = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
			Joueur j = /*(Joueur)*/it.next();
			
			j.main.clear();// supprimer toutes les cartes de la main du joueur
			j.carteRevelee.clear(); // supprimer toutes les cartes faussées du joueur sélectionné
			
			j.elimine = false; // On remet chaque joueur en jeu 
			j.revealed = false;
			
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
    
    public void finRound() {
    	System.out.println("Le round est terminé.");
    	System.out.println("--------------------------");
    	Joueur joueurRes = null;
    	Joueur j = null;
		Iterator<Joueur> it = this.joueur.iterator();
		while(it.hasNext()) {
			joueurRes = /*(Joueur)*/it.next();
			if(joueurRes.elimine == false && joueurRes.revealed == false) {
				j = joueurRes;
			}
		}
		
		System.out.println("Le joueur "+j.numJoueur+" est le dernier joueur non révélé du round ");
    	System.out.println("--------------------------");

		if(j.role.equalsIgnoreCase(this.w)) {
			j.point += 2;
			System.out.println("Il est witch donc il gagne 2 points ");
	    	System.out.println("--------------------------");

		}
		else if(j.role.equalsIgnoreCase(this.v)) {
			j.point++;
			System.out.println("Il est villager donc il gagne 1 point ");
	    	System.out.println("--------------------------");


		}
		
		System.out.println("Récapitulatif des points de chaque joueur : ");
		Iterator<Joueur> it1 = this.joueur.iterator();
		while(it1.hasNext()) {
			joueurRes = /*(Joueur)*/it1.next();
			System.out.println("Joueur "+joueurRes.numJoueur+" : "+joueurRes.point+" point(s)");
		}
    	System.out.println("--------------------------");

		
		this.round++; // on incrémente l'attribut round

    }
    
    public void effetWitch(CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
		Joueur j = this.joueur.get(numJoueurAccuse-1); // joueur accusé
		Joueur j1 = this.joueur.get(numJoueurAccusant-1); // joueur accusant

    	if(carte.witch == Witch.ANGRYMOB || carte.witch == Witch.BROOMSTICK || carte.witch == Witch.WART || carte.witch == Witch.TOAD || carte.witch == Witch.BLACKCAT || carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		System.out.println("Vous prenez un autre tour");
    		System.out.println("------------------------");
    		this.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){ // reprendre carte révélée
    		if(j.estVide(j.main)==false) {
    			System.out.println("Vous reprenez une carte que vous avez révélé");
        		System.out.println("------------------------");
        		j.main.add(j.carteRevelee.get(0));
        		j.carteRevelee.remove(0); // on enlève la carte des cartes révélées
    		}
    		else {
    			System.out.println("Vous n'avez révélé aucune carte");
        		System.out.println("------------------------");
    		}
    		
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // prendre une carte de l'adversaire
    		System.out.println("Vous prenez une carte au hasard du joueur accusant");
    		System.out.println("------------------------");
    		j.main.add(j1.main.get(0));
    		j1.main.remove(0); // on enlève la carte de la main du joueur accusant
    		System.out.println("Voici votre main à présent :");
    		System.out.println("------------------------");
    		System.out.println(j.main);
    		System.out.println("------------------------");
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
    		
    		this.turn = scanner.nextInt();
    		System.out.println("------------------------");
    		
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
    		System.out.print("Entrer le numéro du joueur dont vous voulez révéler l'identité :");
    		int numJoueurChoisi = scanner.nextInt();
    		System.out.println("------------------------");
    		
    		Joueur j1 = this.joueur.get(numJoueurChoisi-1);
    		System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);
    		System.out.println("------------------------");

    		j1.revealed = true; // le joueur a révélé son identité
    		if(j1.role.equalsIgnoreCase(this.w)) {
    			System.out.println("Vous gagnez 2 points et vous jouez le prochain tour");
        		System.out.println("------------------------");
    			j.point+=2;
    			this.turn = numJoueur;
    		}
    		else if(j1.role.equalsIgnoreCase(this.v)){
    			System.out.println("Vous perdez 2 points et le joueur choisi prend le prochain tour");
        		System.out.println("------------------------");
    			j.point-=2;
    			if(j.point < 0) { // on vérifie que le score du joueur n'est pas négatif
    				j.point=0;
    			}
    			this.turn = j1.numJoueur; // on donne la main au joueur dont l'identité a été révélé
    		}
    		


    	}
    	else if(carte.hunt==Hunt.INQUISITION) {
			System.out.println("----------------------------------") ;
			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;
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
			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("Voici l'identité du joueur que vous avez choisi (à regarder discrètement) : "+this.joueur.get(this.turn-1).role) ;
    		System.out.println("----------------------------------") ;

    	}
    	else if(carte.hunt==Hunt.HOOKEDNOSE) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
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
    		System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    		System.out.println("----------------------------------") ;

    		
    	}
    	else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le numéro du joueur que vous voulez choisir : ");
    		int numJ = scanner.nextInt();
    		System.out.println("----------------------------------") ;
    		Joueur j1 = this.joueur.get(numJ-1);
    		System.out.println("Joueur n°"+numJ+" : Révéler votre identité(1) ou supprimer une carte(2) ? ");
    		if(scanner.nextInt()==1) {
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur n°"+numJ+" : vous êtes un "+j1.role);
        		j1.revealed = true;
        		if(j1.role.equalsIgnoreCase(this.w)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous gagnez 1 point et vous prenez le prochain tour") ;
            		j.point++;
        			this.turn=numJoueur;
        		}
        		else if(j1.role.equalsIgnoreCase(this.v)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous perdez 1 point et il prend le prochain tour") ;
            		j.point--;
        			this.turn=numJ;
        		}
        		System.out.println("----------------------------------") ;
        		System.out.println("Vos points : "+j.point);

    		}
    		else if(scanner.nextInt()==2) { // il supprime une carte
        		System.out.println("----------------------------------") ;
        		System.out.println(j1) ;
        		System.out.print("Entrez le numéro de la carte que vous voulez supprimer : ") ;
        		int numCarte = scanner.nextInt();
    			j1.carteRevelee.add(j1.main.get(numCarte-1));
    			j1.main.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Carte supprimée !!!") ;
        		System.out.println("----------------------------------") ;
        		System.out.println(j.main);

    		}
    		
    	}
    	else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous devez révéler votre identité : vous êtes un "+j.role);
    		j.revealed = true;
    		if(j.role.equalsIgnoreCase(this.w)) {
    			this.turn++;
    			j.elimine = true;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
    		}
    	}
    	else if(carte.hunt == Hunt.EVILEYE) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
    		this.turn = scanner.nextInt();
    	}
    	else if(carte.hunt == Hunt.BLACKCAT) {
    		if(j.estVide(j.carteRevelee)==false) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Voici les cartes supprimées : ");
        		System.out.println(j.carteRevelee);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous avez choisi n'a révélé aucune carte ");
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous prenez le prochain tour");
    		
    		this.turn = numJoueur;
    		
    	}
    	else if(carte.hunt == Hunt.PETNEWT) {
    		System.out.println("----------------------------------") ;
    		System.out.print("Choisir un joueur pour lui prendre une carte rumeur qu'il a révélé : ");
    		int numJ = scanner.nextInt();
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		if(j1.estVide(j.carteRevelee)==false) {
    			System.out.println(j1.carteRevelee);
        		System.out.println("----------------------------------") ;
        		System.out.print("Choisir une carte qu'il a révélé (entrez le numéro de la carte : ");
        		int numCarte = scanner.nextInt();
        		j.main.add(j1.carteRevelee.get(numCarte-1)); // le joueur prend la carte révélé d'un autre joueur
        		j1.carteRevelee.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Vous avez pris sa carte révélée avec succès !!! ");
        		System.out.println("----------------------------------") ;
        		System.out.println(j.main);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a révélé aucune carte ");
    		}
    		
    		
    		//choisir le prochain joueur
    		System.out.println("----------------------------------") ;
    		System.out.print("Choisissez le prochain joueur (numéro) : ");
    		this.turn = scanner.nextInt();
    		

    		}

    	System.out.println("----------------------------------") ;
		System.out.println("Au tour du joueur n°"+ this.turn); // on affiche le numéro du prochain joueur
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
    	
    	
    	
    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	Joueur joueurActuel = null;


    	
    	// ----------- boucle de la partie ---------------
    	while (partie.gagne() == 0) { 
    		
    		System.out.print("Qui commence ? "); // Choisir qui commence au début du jeu
        	partie.turn=scanner.nextInt();
        	
        	String test = scanner.nextLine();    	

        	
        	System.out.println("--------------- Début du Round "+partie.round+" ------------------");
        	
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			System.out.println("Joueur n° "+j.getNumJoueur()+" - Choisir votre rôle : witch ou villager ?");
    			String role = scanner.nextLine();
    	    	System.out.println("---------------------------------");
        		
        		
        		j.setRole(role);
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurRevele < nbJoueurTot-1 && partie.gagne() == 0) { // on vérifie qu'il reste assez de joueur pour jouer le round et on vérifie si un joueur a atteint le nombre de points max
    			
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
    			System.out.println("Au tour du joueur n° "+joueurActuel.getNumJoueur());
    			
        		System.out.println(joueurActuel); //afficher le numéro et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie); // le joueur qui a la main joue
        		
        		partie.countJoueurRevele();
        		partie.countJoueurElimine();
        		
        		
        		/*partie.turn++;
        		
        		if(partie.turn>nbJoueurTot) { // retourner au joueur 1 après le tour du dernier joueur
        			partie.turn = 1;
        		}
        		
        		*/
        		
    		}
    		
    		// Fin du round : on distribue les points au joueur restant non révélé
    		
    		partie.finRound();
    		
    		
    		
    		
    	}
    	
    	partie.gagnant = partie.gagne();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }

}

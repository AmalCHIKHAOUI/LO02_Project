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
    protected static final String w="witch"; 
    protected static final String v="villager";
    
    private static boolean check = false; // à utiliser pour vérifier des conditions d'entrée pour l'utilisateur

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
    			Bot nouveauBot = new Bot(i);
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

		if(j.role.equalsIgnoreCase(w)) {
			j.point += 2;
			System.out.println("Il est witch donc il gagne 2 points ");
	    	System.out.println("--------------------------");

		}
		else if(j.role.equalsIgnoreCase(v)) {
			j.point++;
			System.out.println("Il est villager donc il gagne 1 point ");
	    	System.out.println("--------------------------");


		}

		
		this.round++; // on incrémente l'attribut round

    }
    
/*    public void effetWitch(CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
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
        		System.out.println("Voici votre main à présent : ");
        		System.out.println(j.main);
        		System.out.println("------------------------");
        		
    		}
    		else {
    			System.out.println("Vous n'avez révélé aucune carte");
        		System.out.println("------------------------");
    		}
    		
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // prendre une carte de l'adversaire
    		
    		if(j1.estVide(j1.main) == false && j1.main.size() > 1 ) { // le joueur accusé peut prendre une carte si la main de l'adversaire n'est pas vide 
    			System.out.println("Vous prenez une carte au hasard du joueur accusant");
        		System.out.println("------------------------");
    			j.main.add(j1.main.get(0));
        		j1.main.remove(0); // on enlève la carte de la main du joueur accusant
        		System.out.println("Voici votre main à présent :");
        		System.out.println("------------------------");
        		System.out.println(j.main);
        		System.out.println("------------------------");
    		}
    		else {
    			System.out.println("Le joueur accusant n'a plus de carte rumeur. Vous ne lui prenez rien");
        		System.out.println("------------------------");
    		}
    		
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		check = false;
    		while(check==false) {
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn != numJoueurAccuse && this.turn > 0 && this.turn <= (this.nbOrdi + this.nbJoueur) && this.joueur.get(this.turn-1).elimine==false) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		
    		System.out.println("------------------------");
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		System.out.println("Le joueur accusant doit jeter une carte");
    		System.out.println("------------------------");
    		if(j1.estVide(j1.main)==false) {
    			j1.melanger();
        		j1.carteRevelee.add(j1.main.get(0));
        		j1.main.remove(0);
        		System.out.println("Voici la main du joueur accusé après avoir jeté une carte : ");
        		System.out.println(j1.main);
        		System.out.println("------------------------");
    		}
    		else {
        		System.out.println("Malheureusement le joueur accusant n'a plus de cartes rumeurs. Vous venez d'utiliser inutilement votre carte !");
        		System.out.println("------------------------");

    		}
    		System.out.println("Vous prenez le tour suivant");
    		System.out.println("------------------------");
    		this.turn = numJoueurAccuse;
    		
    	}
    	
    }

    public void effetHunt(CarteRumeur carte, int numJoueur) {
		Joueur j = this.joueur.get(numJoueur-1);
		int nbJoueurTot = this.nbJoueur + this.nbOrdi;

    	if(carte.hunt == Hunt.ANGRYMOB){ // révéler la carte d'un autre joueur
    		int numJoueurChoisi = 0;
    		check = false;
    		
    		while(check==false) {
    			System.out.println("------------------------");
        		System.out.print("Entrer le numéro du joueur dont vous voulez révéler l'identité :");
        		numJoueurChoisi = scanner.nextInt();
        		if(numJoueurChoisi > 0 && numJoueurChoisi <= nbJoueurTot && this.joueur.get(numJoueurChoisi-1).revealed==false) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		Joueur j1 = this.joueur.get(numJoueurChoisi-1);
    		System.out.println("------------------------");
    		System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);

    		j1.revealed = true; // le joueur a révélé son identité
    		if(j1.role.equalsIgnoreCase(w)) {
        		System.out.println("------------------------");
    			System.out.println("Vous gagnez 2 points et vous jouez le prochain tour");
        		j1.elimine=true;
    			j.point+=2;
    			this.turn = numJoueur;
    		}
    		else if(j1.role.equalsIgnoreCase(v)){
        		System.out.println("------------------------");
    			System.out.println("Vous perdez 2 points et le joueur choisi prend le prochain tour");
    			j.point-=2;
    			if(j.point < 0) { // on vérifie que le score du joueur n'est pas négatif
    				j.point=0;
    			}
    			this.turn = j1.numJoueur; // on donne la main au joueur dont l'identité a été révélé
    		}

    	}
    	else if(carte.hunt==Hunt.INQUISITION) {
			
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Voici l'identité du joueur que vous avez choisi (à regarder discrètement) : "+this.joueur.get(this.turn-1).role) ;

    	}
    	else if(carte.hunt==Hunt.POINTEDHAT) {
    		System.out.println("----------------------------------") ;
			System.out.println("L'effet Hunt de cette carte vous permet donc de reprendre une de vos cartes révélées");
			if(j.estVide(j.carteRevelee)==true) {
	    		System.out.println("----------------------------------") ;
				System.out.println("Vous n'avez pas encore révélé de cartes ! ");
			}
			else {
				j.main.add(j.carteRevelee.get(0)); // on insère la carte révélée dans votre main
				j.carteRevelee.remove(0);
			}
    		
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Voici l'identité du joueur que vous avez choisi (à regarder discrètement) : "+this.joueur.get(this.turn-1).role) ;

    	}
    	else if(carte.hunt==Hunt.HOOKEDNOSE) {
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous avez la possibilité de lui prendre une carte aléatoire") ;
    		Joueur j1 = this.joueur.get(this.turn-1);
    		if(j1.estVide(j1.main)==false) {
    			j.melanger(); // mélanger les cartes du joueur
    			j.main.add(j1.main.get(0)); // le joueur j prend une carte aléatoire à j1
    			j1.main.remove(0);
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous lui avez pris une carte avec succès !!!!") ;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a plus de carte dans sa main !!") ;
    		}

    	}
    	else if(carte.hunt == Hunt.BROOMSTICK || carte.hunt == Hunt.WART){ //choisir le prochain joueur
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}

    		
    	}
    	else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    		
    		int numJ = 0;
    		int action = 0;
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
        		System.out.print("Entrez le numéro du joueur que vous voulez choisir : ");
        		numJ = scanner.nextInt();
        		if(numJ > 0 && numJ <= nbJoueurTot && this.joueur.get(numJ-1).elimine==false && numJ != numJoueur && (this.joueur.get(numJ-1).revealed==false || this.joueur.get(numJ-1).estVide(this.joueur.get(numJ-1).main)==false)) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		System.out.println("----------------------------------") ;
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		check = false;
    		
    		while(check==false) {
    			System.out.println("Joueur n°"+numJ+" : Révéler votre identité(1) ou supprimer une carte(2) ? ");
        		action = scanner.nextInt();
        		if((action==1 && j1.revealed==false) || (action==2 && j1.estVide(j1.main))) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		if(action==1) {
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur n°"+numJ+" : vous êtes un "+j1.role);
        		j1.revealed = true;
        		if(j1.role.equalsIgnoreCase(w)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous gagnez 1 point et vous prenez le prochain tour") ;
            		j.point++;
        			this.turn=numJoueur;
        		}
        		else if(j1.role.equalsIgnoreCase(v)) {
            		System.out.println("----------------------------------") ;
            		System.out.println("Vous perdez 1 point et il prend le prochain tour") ;
            		j.point--;
            		if(j.point < 0) { // on vérifie que le score du joueur n'est pas négatif
        				j.point=0;
        			}
        			this.turn=numJ;
        		}

    		}
    		else if(action==2) { // il supprime une carte
    			int numCarte = 0;
        		System.out.println("----------------------------------") ;
        		System.out.println(j1) ;
        		
        		check = false;
        		
        		while(check==false) {
        			System.out.println("----------------------------------") ;
            		System.out.print("Joueur n°"+numJ+" : Entrez le numéro de la carte que vous voulez supprimer : ") ;
            		numCarte = scanner.nextInt();
            		if(numCarte > 0 && numCarte <= j1.main.size()) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte");
                    	System.out.println("---------------------------------");
            		}
        		}
    			j1.carteRevelee.add(j1.main.get(numCarte-1));
    			j1.main.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Carte supprimée !!!") ;
        		System.out.println(j.main);

    		}
    		
    	}
    	else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous devez révéler votre identité : vous êtes un "+j.role);
    		j.revealed = true;
    		if(j.role.equalsIgnoreCase(w)) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous êtes witch, vous êtes donc éliminés du round !! Le joueur suivant joue le prochain tour. ");
    			this.turn++;
    			j.elimine = true;
    			if(this.turn>nbJoueurTot) { // retourner au joueur 1 après le tour du dernier joueur
        			this.turn = 1;
        		}
    			
    		}
    		else {
    			check = false;
        		
        		while(check==false) {
        			System.out.println("----------------------------------") ;
        			System.out.print("Vous êtes villager, entrez le numéro du joueur qui joue le prohain tour : ");
            		this.turn = scanner.nextInt();
            		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte");
                    	System.out.println("---------------------------------");
            		}
        		}
    		}
    	}
    	else if(carte.hunt == Hunt.EVILEYE) {
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
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
    		
    		int numJ = 0;
    		
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
        		System.out.print("Choisir un joueur pour lui prendre une carte rumeur qu'il a révélé : ");
        		numJ = scanner.nextInt();
        		if(numJ > 0 && numJ <= nbJoueurTot && this.joueur.get(numJ-1).elimine==false && numJ != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		if(j1.estVide(j.carteRevelee)==false) { // si le joueur choisi a révélé une ou plusieurs cartes
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
        		System.out.println("Le joueur que vous avez choisi n'a révélé aucune carte !!! Vous venez d'utiliser une carte inutilement. ");
    		}
    		
    		
    		//choisir le prochain joueur
    		check = false;
    		
    		while(check==false) {
    			System.out.println("----------------------------------") ;
    			System.out.print("Entrez le numéro du joueur qui joue le prohain tour : ");
        		this.turn = scanner.nextInt();
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
        		}
    		}
    		

    		}

    	System.out.println("----------------------------------") ;
		System.out.println("Au tour du joueur n°"+ this.turn); // on affiche le numéro du prochain joueur
    }
    
    */
    
    public static void main(String[] args) {
    	System.out.println("---------------------------------");
    	System.out.println("Bienvenue dans le Jeu Witch Hunt");
    	System.out.println("---------------------------------");
    	
    	int nbJoueur = 0;
    	int nbOrdi = 0;
    	int nbJoueurTot = 0;
    	check = false;
    	JeuCartes jeu = null;
    	while(check == false) {
    		jeu = new JeuCartes();
        	System.out.print("Combien de joueurs physiques : ");
        	nbJoueur = scanner.nextInt();
        	System.out.print("Combien de joueurs virtuels : ");
        	nbOrdi = scanner.nextInt();
        	nbJoueurTot = nbJoueur + nbOrdi ;
        	if(nbJoueurTot >= 3 && nbJoueurTot <= 6) {
        		check = true;
        		
        	}
        	else {
        		System.out.println("---------------------------------");
            	System.out.println("La saisie est incorrecte");
            	System.out.println("---------------------------------");
        	}
    	}
    	

    	Partie partie = new Partie(nbJoueur, nbOrdi, jeu);
    	
    	
    	partie.debutJeu();
    	
    	
    	
    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	Joueur joueurActuel = null;


    	
    	// ----------- boucle de la partie ---------------
    	while (partie.gagne() == 0) { 
    		
    		check = false;
    		while(check == false) {
    			System.out.print("Qui commence ? "); // Choisir qui commence au début du jeu
            	partie.turn=scanner.nextInt();
            	if(partie.turn > 0 && partie.turn <= nbJoueurTot) {
            		check = true;
            	}
            	else {
            		System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte");
                	System.out.println("---------------------------------");
            	}
    		}
    		
        	
        	String test = scanner.nextLine();    	

        	
        	System.out.println("--------------- Début du Round "+partie.round+" ------------------");
        	
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			j.chooseRole();
    			
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurRevele < nbJoueurTot-1 && partie.gagne() == 0) { // on vérifie qu'il reste assez de joueur pour jouer le round et on vérifie si un joueur a atteint le nombre de points max
    			
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
    			System.out.println("Au tour du joueur n° "+ joueurActuel.getNumJoueur());
    			
        		System.out.println(joueurActuel); //afficher le numéro et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie, nbJoueurTot); // le joueur qui a la main joue
        		
        		partie.countJoueurRevele();
        		partie.countJoueurElimine();
        		
        		// ------- Récap des points de chaque joueur ----------
        		System.out.println("Récapitulatif des points de chaque joueur : ");
        		Iterator<Joueur> it1 = partie.joueur.iterator();
        		Joueur j1 = null;
        		while(it1.hasNext()) {
        			j1 = /*(Joueur)*/it1.next();
        			System.out.println("Joueur "+j1.numJoueur+" : "+j1.point+" point(s)");
        		}
            	System.out.println("--------------------------");
        		/*partie.turn++;
        		
        		
        		
        		*/
        		
    		}
    		
    		// Fin du round : on distribue les points au joueur restant non révélé
    		
    		partie.finRound();
    		
    		
    		
    		
    	}
    	
    	partie.gagnant = partie.gagne();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }

}

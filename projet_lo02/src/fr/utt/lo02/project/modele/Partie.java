package fr.utt.lo02.project.modele;

import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Partie extends Observable {
    protected Partie objetUnique;
    protected int round;
    protected int turn;
    protected int nbJoueur;
    protected int nbOrdi;
    protected int gagnant;
    
    public boolean partieGUI;
    /**
     * Bool�en utilis� pour v�rifier si la partie est execut�e en ligne de commande ou avec le GUI
     */
    
    protected int nbJoueurElimine;
    protected int nbJoueurRevele;


    // constante utilisee pour savoir si un joueur est witch ou villager
    protected static final String w="witch"; 
    protected static final String v="villager";
    
    private static boolean check = false; // a utiliser pour verifier des conditions d'entree pour l'utilisateur

    public ArrayList<Joueur> joueur = new ArrayList<Joueur> ();
    public static Scanner scanner = new Scanner(System.in);
	public JeuCartes jeu;

    


    
    public Partie(int nbJoueur, int nbOrdi, JeuCartes jeu, boolean partieGUI) {
    	this.nbJoueur = nbJoueur;
    	this.nbOrdi = nbOrdi;
    	this.round = 1;
    	this.turn = 1;
    	this.gagnant = 0;
    	this.jeu = jeu;
    	this.nbJoueurElimine = 0;
    	this.nbJoueurRevele = 0;
    	this.partieGUI=partieGUI;


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
    	this.setChanged();
    	this.notifyObservers();
    	
    	}

    public Partie getInstance() {
    	return this;
    }
    
    public void countJoueurElimine() {
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non elimines : ");
    	this.nbJoueurElimine = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.elimine == true) {
				this.nbJoueurElimine++;
			}
			else {
		    	System.out.println("Joueur "+j.numJoueur);
			}
			
		}
    	System.out.println("----------------------------------- ");

    }
    public void countJoueurRevele() {
    	System.out.println("----------------------------------- ");
    	System.out.println("Voici les joueurs non reveles : ");
    	this.nbJoueurRevele = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { 
			Joueur j = (Joueur)it.next();
			if (j.revealed == true) {
				this.nbJoueurRevele++;
			}
			else {
		    	System.out.println("Joueur "+j.numJoueur);
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
    	System.out.println("Le joueur " + numJoueur + " a gagne");
    	System.out.println("--------------------------");
    	System.out.println("Le jeu est termine !!!!!!");
    	System.out.println("--------------------------");
    }
    public void debutRound(int nbJoueur) { // on distribue les cartes a tout les joueurs
    	
    	
    	this.nbJoueurElimine = 0; // on reset le nb de joueurs elimines a 0
    	this.nbJoueurRevele = 0;
    	this.jeu.melanger(); // melanger les cartes
    	int tempNbCarte = 0;
    	for(Iterator<Joueur> it = this.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
			Joueur j = /*(Joueur)*/it.next();
			
			j.main.clear();// supprimer toutes les cartes de la main du joueur
			j.carteRevelee.clear(); // supprimer toutes les cartes faussees du joueur selectionne
			
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
    	this.setChanged();
    	this.notifyObservers();
    	
    }
    
    public void finRound() {
    	System.out.println("Le round est termine.");
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
		
		System.out.println("Le joueur "+j.numJoueur+" est le dernier joueur non revele du round ");
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
		// ------- R�cap des points de chaque joueur ----------
		System.out.println("R�capitulatif des points de chaque joueur : ");
		Iterator<Joueur> it1 = this.joueur.iterator();
		Joueur j1 = null;
		while(it1.hasNext()) {
			j1 = /*(Joueur)*/it1.next();
			System.out.println("Joueur "+j1.numJoueur+" : "+j1.point+" point(s)");
		}
    	System.out.println("--------------------------");

		
		this.round++; // on incremente l'attribut round

    }
    
    public void effetWitch(CarteRumeur carte, int numJoueurAccusant, int numJoueurAccuse) {
		Joueur j = this.joueur.get(numJoueurAccuse-1); // joueur accuse
		Joueur j1 = this.joueur.get(numJoueurAccusant-1); // joueur accusant

    	if(carte.witch == Witch.ANGRYMOB || carte.witch == Witch.BROOMSTICK || carte.witch == Witch.WART || carte.witch == Witch.TOAD || carte.witch == Witch.BLACKCAT || carte.witch == Witch.PETNEWT) { //prendre le prochain tour
    		System.out.println("---------------------------------");
    		System.out.println("Vous prenez un autre tour");
    		this.turn = numJoueurAccuse;
    	}
    	else if(carte.witch == Witch.INQUISITION){
    		if(j.main.size()>1) {
    			int numCarte = 0;
        		check = false;
        		while(check==false) {
    	    		System.out.println("---------------------------------");
        			System.out.print("Entrez le numero de la carte que vous voulez supprimer :  ");
        			if (!(j instanceof Bot )) {
        				// le joueur est physique
        				numCarte = scanner.nextInt();
        			} else {
        				// le joueur est un bot
        				
        				Bot bot = (Bot)j;
        				numCarte = bot.chooseCard();
        			}
            		
            		
            		if(numCarte > 0 && numCarte <= j.main.size() && carte != this.joueur.get(numJoueurAccuse-1).main.get(numCarte-1)) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte 18");
                    	System.out.println("---------------------------------");
            		}
        		}
        		j.carteRevelee.add(j.main.get(numCarte-1));
			    j.main.remove(numCarte-1);
	    		System.out.println("---------------------------------");
			    System.out.println("Carte supprimee !!!") ;
			    System.out.println("---------------------------------");
			    System.out.println("Voici votre jeu a present : ") ;
			    System.out.println(j.main);
    		}
    		else {
	    		System.out.println("---------------------------------");
    			System.out.println("Vous n'avez plus de cartes ! Aucune carte n'a ete supprimee.  ");
    		}
    		System.out.println("---------------------------------");
    		System.out.println("Vous prenez un autre tour");
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.POINTEDHAT){ // reprendre carte revelee
    		if(j.estVide(j.carteRevelee)==false) {
	    		System.out.println("---------------------------------");
    			System.out.println("Vous reprenez une carte que vous avez revele");
        		j.main.add(j.carteRevelee.get(0));
        		j.carteRevelee.remove(0); // on enleve la carte des cartes revelees
        		System.out.println("---------------------------------");
        		System.out.println("Voici votre main a present : ");
        		System.out.println(j.main);
        		
    		}
    		else {
    			System.out.println("---------------------------------");
    			System.out.println("Vous n'avez revele aucune carte");
    		}
    		
    		this.turn = numJoueurAccuse;

    	}
    	else if(carte.witch == Witch.HOOKEDNOSE){ // prendre une carte de l'adversaire
    		
    		if(j1.estVide(j1.main) == false && j1.main.size() > 1 ) { // le joueur accuse peut prendre une carte si la main de l'adversaire n'est pas vide 
    			System.out.println("---------------------------------");
    			System.out.println("Vous prenez une carte au hasard du joueur accusant");
    			j.main.add(j1.main.get(0));
        		j1.main.remove(0); // on enleve la carte de la main du joueur accusant
        		System.out.println("---------------------------------");
        		System.out.println("Voici votre main a present :");
        		
        		System.out.println(j.main);
    		}
    		else {
    			System.out.println("---------------------------------");
    			System.out.println("Le joueur accusant n'a plus de carte rumeur. Vous ne lui prenez rien");
    		}
    		
    		this.turn = numJoueurAccuse;
    		
    	}
    	else if(carte.witch == Witch.DUCKINGSTOOL || carte.witch == Witch.EVILEYE){ //choisir le prochain joueur
    		check = false;
    		while(check==false) {
    			
        		
        		if (!(j instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("---------------------------------");
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("---------------------------------");
        			System.out.println("Le bot choisit le prochain joueur ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		if(this.turn != numJoueurAccuse && this.turn > 0 && this.turn <= (this.nbOrdi + this.nbJoueur) && this.joueur.get(this.turn-1).elimine==false) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 1 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		
    		
    	}
    	else if(carte.witch == Witch.CAULDRON){ //Le joueur qui vous accuse jette une carte et vous prenez le prochain tour
    		System.out.println("---------------------------------");
    		System.out.println("Le joueur accusant doit jeter une carte");
    		if(j1.estVide(j1.main)==false) {
    			j1.melanger();
        		j1.carteRevelee.add(j1.main.get(0));
        		j1.main.remove(0);
        		System.out.println("---------------------------------");
        		System.out.println("Voici la main du joueur accusant apres avoir jete une carte : ");
        		System.out.println(j1.main);
    		}
    		else {
    			System.out.println("---------------------------------");
        		System.out.println("Malheureusement le joueur accusant n'a plus de cartes rumeurs. Vous venez d'utiliser inutilement votre carte !");

    		}
    		System.out.println("---------------------------------");
    		System.out.println("Vous prenez le tour suivant");
    		this.turn = numJoueurAccuse;
    		
    	}
    	
    }

    public void effetHunt(CarteRumeur carte, int numJoueur) {
		Joueur j = this.joueur.get(numJoueur-1);
		int nbJoueurTot = this.nbJoueur + this.nbOrdi;

    	if(carte.hunt == Hunt.ANGRYMOB){ // reveler la carte d'un autre joueur
    		int numJoueurChoisi = 0;
    		check = false;
    		
    		while(check==false) {
    			
        	
        		if (!(j instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("------------------------");
            		System.out.print("Entrer le numero du joueur dont vous voulez reveler l'identite :");
        			numJoueurChoisi = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("------------------------");
            		System.out.println("Le bot choisit un joueur pour reveler son identite");
    				Bot bot = (Bot)j;
    				numJoueurChoisi = bot.chooseRandomPlayer(this);
    			}
        		if(numJoueurChoisi > 0 && numJoueurChoisi <= nbJoueurTot && this.joueur.get(numJoueurChoisi-1).revealed==false) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 2");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		Joueur j1 = this.joueur.get(numJoueurChoisi-1);
    		System.out.println("------------------------");
    		System.out.println("Le joueur que vous avez choisi est ... un "+ j1.role);

    		j1.revealed = true; // le joueur a revele son identite
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
    			if(j.point < 0) { // on verifie que le score du joueur n'est pas negatif
    				j.point=0;
    			}
    			this.turn = j1.numJoueur; // on donne la main au joueur dont l'identite a ete revele
    		}

    	}
    	else if(carte.hunt==Hunt.INQUISITION) {
			
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Le bot choisit le joueur suivant ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 3");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Voici l'identite du joueur que vous avez choisi (A regarder discretement) : "+this.joueur.get(this.turn-1).role) ;

    	}
    	else if(carte.hunt==Hunt.POINTEDHAT) {
    		System.out.println("----------------------------------") ;
			System.out.println("L'effet Hunt de cette carte vous permet donc de reprendre une de vos cartes revelees");
			if(j.estVide(j.carteRevelee)==true) {
	    		System.out.println("----------------------------------") ;
				System.out.println("Vous n'avez pas encore revele de cartes ! ");
			}
			else {
				j.main.add(j.carteRevelee.get(0)); // on insere la carte revelee dans votre main
				j.carteRevelee.remove(0);
			}
    		
    		check = false;
    		
    		while(check==false) {
    			
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Entrez le numero du joueur qui joue le prohain tour : ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 4 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Voici l'identite du joueur que vous avez choisi (a regarder discretement) : "+this.joueur.get(this.turn-1).role) ;

    	}
    	else if(carte.hunt==Hunt.HOOKEDNOSE) {
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Entrez le numero du joueur qui joue le prohain tour : ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 5 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous avez la possibilite de lui prendre une carte aleatoire") ;
    		Joueur j1 = this.joueur.get(this.turn-1);
    		if(j1.estVide(j1.main)==false) {
    			j.melanger(); // melanger les cartes du joueur
    			j.main.add(j1.main.get(0)); // le joueur j prend une carte aleatoire a j1
    			j1.main.remove(0);
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous lui avez pris une carte avec succes !!!!") ;
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a plus de carte dans sa main !!") ;
    		}

    	}
    	else if(carte.hunt == Hunt.BROOMSTICK || carte.hunt == Hunt.WART){ //choisir le prochain joueur
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Entrez le numero du joueur qui joue le prohain tour : ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 6 ");
                	System.out.println("---------------------------------");
        		}
    		}

    		
    	}
    	else if(carte.hunt == Hunt.DUCKINGSTOOL) {
    		
    		int numJ = 0;
    		int action = 0;
    		check = false;
    		
    		while(check==false) {
    			
        		if (!(j instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("----------------------------------") ;
            		System.out.print("Entrez le numero du joueur que vous voulez choisir : ");
        			numJ = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
            		System.out.println("Entrez le numero du joueur que vous voulez choisir : ");
    				Bot bot = (Bot)j;
    				numJ = bot.chooseRandomPlayer(this);
    			}
        		if(numJ > 0 && numJ <= nbJoueurTot && this.joueur.get(numJ-1).elimine==false && numJ != numJoueur && (this.joueur.get(numJ-1).revealed==false || this.joueur.get(numJ-1).estVide(this.joueur.get(numJ-1).main)==false)) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 7 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		System.out.println("----------------------------------") ;
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j1 instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("----------------------------------") ;
    				System.out.print("Joueur "+numJ+" : Reveler votre identite(1) ou supprimer une carte(2) ? ");
        			action = scanner.nextInt();
    			} else {
    				// le joueur est un bot
        			System.out.println("----------------------------------") ;
    				System.out.println("Joueur "+numJ+" : Reveler votre identite(1) ou supprimer une carte(2) ? ");
    				Bot bot = (Bot)j1;
    				action = bot.revealOrDelete() ;
    			}
    			
        		if((action==1 && j1.revealed==false) || (action==2 && j1.estVide(j1.main)==false)) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 8");
                	System.out.println("---------------------------------");
        		}
    		}
    		if(action==1) {
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur "+numJ+" : vous etes un "+j1.role);
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
            		if(j.point < 0) { // on verifie que le score du joueur n'est pas negatif
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
        			
            		if (!(j1 instanceof Bot )) {
        				// le joueur est physique
            			System.out.println("----------------------------------") ;
                		System.out.print("Joueur "+numJ+" : Entrez le numero de la carte que vous voulez supprimer : ") ;
            			numCarte = scanner.nextInt();
        			} else {
        				// le joueur est un bot
        				System.out.println("----------------------------------") ;
                		System.out.println("Joueur "+numJ+" : Entrez le numero de la carte que vous voulez supprimer : ") ;
        				Bot bot = (Bot)j1;
        				numCarte = bot.chooseCard() ;
        			}
            		
            		if(numCarte > 0 && numCarte <= j1.main.size()) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte 9 ");
                    	System.out.println("---------------------------------");
            		}
        		}
    			j1.carteRevelee.add(j1.main.get(numCarte-1));
    			j1.main.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Carte supprimee !!!") ;
        		System.out.println("----------------------------------") ;
        		System.out.println("Joueur "+numJ+", vous prenez le prochain tour et voici votre jeu actualis� : ") ;
        		System.out.println(j1.main);
        		
        		this.turn = numJ;

    		}
    		/*else {
    			System.out.println("---------------------------------");
            	System.out.println("Le joueur selectionne est deja revele et n'a plus de cartes a la main ");
            	System.out.println("---------------------------------");
    		}*/
    		
    	}
    	else if(carte.hunt == Hunt.CAULDRON || carte.hunt == Hunt.TOAD) { 
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous devez reveler votre identite : vous etes un "+j.role);
    		j.revealed = true;
    		if(j.role.equalsIgnoreCase(w)) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous etes witch, vous etes donc elimines du round !! Le joueur suivant joue le prochain tour. ");
    			this.turn++;
    			j.elimine = true;
    			if(this.turn>nbJoueurTot) { // retourner au joueur 1 apres le tour du dernier joueur
        			this.turn = 1;
        		}
    			
    		}
    		else {
    			check = false;
        		
        		while(check==false) {
        			
        			if (!(j instanceof Bot )) {
        				// le joueur est physique
        				System.out.println("----------------------------------") ;
            			System.out.print("Vous etes villager, entrez le numero du joueur qui joue le prohain tour : ");
        				this.turn = scanner.nextInt();
        			} else {
        				// le joueur est un bot
        				System.out.println("----------------------------------") ;
            			System.out.println("Vous etes villager, entrez le numero du joueur qui joue le prohain tour : ");
        				Bot bot = (Bot)j;
        				this.turn = bot.chooseNextPlayer(this);
        			}
        			
            		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
            			check=true;
            		}
            		else {
            			System.out.println("---------------------------------");
                    	System.out.println("La saisie est incorrecte 10 ");
                    	System.out.println("---------------------------------");
            		}
        		}
    		}
    	}
    	else if(carte.hunt == Hunt.EVILEYE) {
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Entrez le numero du joueur qui joue le prohain tour : ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 11");
                	System.out.println("---------------------------------");
        		}
    		}
    	}
    	else if(carte.hunt == Hunt.BLACKCAT) {
    		if(j.estVide(j.carteRevelee)==false) {
    			System.out.println("----------------------------------") ;
        		System.out.println("Voici les cartes supprimees : ");
        		System.out.println(j.carteRevelee);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Vous avez choisi n'a revele aucune carte ");
    		}
    		System.out.println("----------------------------------") ;
    		System.out.println("Vous prenez le prochain tour");
    		
    		this.turn = numJoueur;
    		
    	}
    	else if(carte.hunt == Hunt.PETNEWT) {
    		
    		int numJ = 0;
    		
    		check = false;
    		
    		while(check==false) {
    			
        		
        		if (!(j instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("----------------------------------") ;
            		System.out.print("Choisir un joueur pour lui prendre une carte rumeur qu'il a revele : ");
        			numJ = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
            		System.out.println("Choisir un joueur pour lui prendre une carte rumeur qu'il a revele : ");
    				Bot bot = (Bot)j;
    				numJ = bot.chooseRandomPlayer(this);
    			}
        		if(numJ > 0 && numJ <= nbJoueurTot && this.joueur.get(numJ-1).elimine==false && numJ != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 12 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		
    		Joueur j1 = this.joueur.get(numJ-1);
    		
    		if(j1.estVide(j1.carteRevelee)==false) { // si le joueur choisi a revele une ou plusieurs cartes
    			System.out.println(j1.carteRevelee);
        		
        		int numCarte;
        		if (!(j instanceof Bot )) {
    				// le joueur est physique
        			System.out.println("----------------------------------") ;
            		System.out.print("Choisir une carte qu'il a revele (entrez le numero de la carte : ");
        			numCarte = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
            		System.out.println("Choisir une carte qu'il a revele (entrez le numero de la carte : ");
    				Bot bot = (Bot)j;
    				numCarte = bot.pickRevealedCard(j1);
    			}
        		
        		j.main.add(j1.carteRevelee.get(numCarte-1)); // le joueur prend la carte revele d'un autre joueur
        		j1.carteRevelee.remove(numCarte-1);
        		System.out.println("----------------------------------") ;
        		System.out.println("Vous avez pris sa carte revelee avec succes !!! ");
        		System.out.println("----------------------------------") ;
        		System.out.println(j.main);
    		}
    		else {
    			System.out.println("----------------------------------") ;
        		System.out.println("Le joueur que vous avez choisi n'a revele aucune carte !!! Vous venez d'utiliser une carte inutilement. ");
    		}
    		
    		
    		//choisir le prochain joueur
    		check = false;
    		
    		while(check==false) {
    			
    			if (!(j instanceof Bot )) {
    				// le joueur est physique
    				System.out.println("----------------------------------") ;
        			System.out.print("Entrez le numero du joueur qui joue le prohain tour : ");
    				this.turn = scanner.nextInt();
    			} else {
    				// le joueur est un bot
    				System.out.println("----------------------------------") ;
        			System.out.println("Entrez le numero du joueur qui joue le prohain tour : ");
    				Bot bot = (Bot)j;
    				this.turn = bot.chooseNextPlayer(this);
    			}
        		if(this.turn > 0 && this.turn <= nbJoueurTot && this.joueur.get(this.turn-1).elimine==false && this.turn != numJoueur) {
        			check=true;
        		}
        		else {
        			System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 13 ");
                	System.out.println("---------------------------------");
        		}
    		}
    		

    		}

    	System.out.println("----------------------------------") ;
		System.out.println("Au tour du joueur "+ this.turn); // on affiche le numero du prochain joueur
    }
    
   
    
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
            	System.out.println("La saisie est incorrecte 14 ");
            	System.out.println("---------------------------------");
        	}
    	}
    	

    	Partie partie = new Partie(nbJoueur, nbOrdi, jeu, false);
    	
    	
    	partie.debutJeu();
    	
    	
    	
    	partie.initListeJoueur(nbJoueur,nbOrdi); //initialiser la liste des joueurs
    	
    	Joueur joueurActuel = null;


    	
    	// ----------- boucle de la partie ---------------
    	while (partie.gagne() == 0) { 
    		
    		check = false;
    		while(check == false) {
    			System.out.print("Qui commence ? "); // Choisir qui commence au debut du jeu
            	partie.turn=scanner.nextInt();
            	if(partie.turn > 0 && partie.turn <= nbJoueurTot) {
            		check = true;
            	}
            	else {
            		System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 15");
                	System.out.println("---------------------------------");
            	}
    		}
    		
        	
        	String test = scanner.nextLine();    	

        	
        	System.out.println("--------------- Debut du Round "+partie.round+" ------------------");
        	
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			j.chooseRole();
    			
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurRevele < nbJoueurTot-1 && partie.gagne() == 0) { // on verifie qu'il reste assez de joueur pour jouer le round et on verifie si un joueur a atteint le nombre de points max
    			
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
    			System.out.println("Au tour du joueur "+ joueurActuel.getNumJoueur());
    			
        		System.out.println(joueurActuel); //afficher le numero et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie, nbJoueurTot); // le joueur qui a la main joue
        		
        		partie.countJoueurRevele();
        		partie.countJoueurElimine();
        		
        		// ------- Recap des points de chaque joueur ----------
        		System.out.println("Recapitulatif des points de chaque joueur : ");
        		Iterator<Joueur> it1 = partie.joueur.iterator();
        		Joueur j1 = null;
        		while(it1.hasNext()) {
        			j1 = /*(Joueur)*/it1.next();
        			System.out.println("Joueur "+j1.numJoueur+" : "+j1.point+" point(s)");
        		}
            	System.out.println("--------------------------");
        		//partie.turn++;
        		
        		
    		}
    		
    		// Fin du round : on distribue les points au joueur restant non revele
    		
    		partie.finRound();
    		
    		
    		
    		
    	}
    	
    	partie.gagnant = partie.gagne();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    }
	public JeuCartes getJeu() {
		return jeu;
	}
	public void setJeu(JeuCartes jeu) {
		this.jeu = jeu;
	}
	public int getTurn() {
		return turn;
	}
	public static String getW() {
		return w;
	}
	
	/*public void jeu() {
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
            	System.out.println("La saisie est incorrecte 14 ");
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
    			System.out.print("Qui commence ? "); // Choisir qui commence au debut du jeu
            	partie.turn=scanner.nextInt();
            	if(partie.turn > 0 && partie.turn <= nbJoueurTot) {
            		check = true;
            	}
            	else {
            		System.out.println("---------------------------------");
                	System.out.println("La saisie est incorrecte 15");
                	System.out.println("---------------------------------");
            	}
    		}
    		
        	
        	String test = scanner.nextLine();    	

        	
        	System.out.println("--------------- Debut du Round "+partie.round+" ------------------");
        	
    		for(Iterator<Joueur> it = partie.joueur.iterator(); it.hasNext(); ) { //choisir le rôle pour chaque joueur
    			Joueur j = (Joueur)it.next();
    			j.chooseRole();
    			
    		}
    		
    		partie.debutRound(nbJoueurTot); // on commence un nouveau round, on distribue les cartes 
    		
    		// ----------- boucle du round ---------------
    		while(partie.nbJoueurRevele < nbJoueurTot-1 && partie.gagne() == 0) { // on verifie qu'il reste assez de joueur pour jouer le round et on verifie si un joueur a atteint le nombre de points max
    			
    			joueurActuel = partie.joueur.get(partie.turn -1);
        		
    			System.out.println("Au tour du joueur "+ joueurActuel.getNumJoueur());
    			
        		System.out.println(joueurActuel); //afficher le numero et les cartes du Joueur
        		
        		partie = joueurActuel.jouer(partie, nbJoueurTot); // le joueur qui a la main joue
        		
        		partie.countJoueurRevele();
        		partie.countJoueurElimine();
        		
        		// ------- Recap des points de chaque joueur ----------
        		System.out.println("Recapitulatif des points de chaque joueur : ");
        		Iterator<Joueur> it1 = partie.joueur.iterator();
        		Joueur j1 = null;
        		while(it1.hasNext()) {
        			j1 = it1.next(); //(Joueur)
        			System.out.println("Joueur "+j1.numJoueur+" : "+j1.point+" point(s)");
        		}
            	System.out.println("--------------------------");
        		//partie.turn++;
        		
        		
        		
        		
        		
    		}
    		
    		// Fin du round : on distribue les points au joueur restant non revele
    		
    		partie.finRound();
    		
    		
    		
    		
    	}
    	
    	partie.gagnant = partie.gagne();
    	partie.finJeu(partie.gagnant);
    	
    	
     	
    } */

}
